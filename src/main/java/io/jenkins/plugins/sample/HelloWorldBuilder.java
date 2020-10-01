package io.jenkins.plugins.sample;

import hudson.Launcher;
import hudson.Extension;
import hudson.EnvVars;
import hudson.FilePath;
import hudson.util.FormValidation;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import java.util.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jenkins.tasks.SimpleBuildStep;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundSetter;

public class HelloWorldBuilder extends Builder implements SimpleBuildStep {

    private final String name;
    private int maxBuildTime;

    @DataBoundConstructor
    public HelloWorldBuilder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMaxBuildTime(int maxBuildTime) {
        this.maxBuildTime = maxBuildTime;
    }

    /*public boolean isUseFrench() {
        return useFrench;
    }*/

    /*@Override
    public void perform(Run<?,?> run, EnvVars env, TaskListener listener) throws InterruptedException, IOException {
        listener.getLogger().println(run.getTimeInMillis() - run.getStartTimeInMillis());
    }*/

    @Override
    public void perform(Run<?,?> run, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
        //listener.getLogger().println("CAN YOU HEAR ME!!!");
        //listener.getLogger().println("OMO");
        //listener.getLogger().println(run.getTimeInMillis() - run.getStartTimeInMillis());
        //listener.getLogger().println(run.getTimeInMillis());
        //listener.getLogger().println(run.getStartTimeInMillis());

        int totTime = 0;
        /*while (true) {
            if (run.isBuilding()) {
                listener.getLogger().println(run.getTimestampString());
            }
            Thread.sleep(5000);
            totTime += 5000;
            if (totTime >= 1000000) break;
        }*/


    }

    /*
    @Override
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
        if (useFrench) {
            listener.getLogger().println("Bonjour, " + name + "!");
        } else {
            listener.getLogger().println("Hello, " + name + "!");
        }
    }*/

    @Symbol("greet")
    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        public FormValidation doCheckName(@QueryParameter String value, @QueryParameter boolean useFrench)
                throws IOException, ServletException {
            if (value.length() == 0)
                return FormValidation.error(Messages.HelloWorldBuilder_DescriptorImpl_errors_missingName());
            if (value.length() < 4)
                return FormValidation.warning(Messages.HelloWorldBuilder_DescriptorImpl_warnings_tooShort());
            if (!useFrench && value.matches(".*[éáàç].*")) {
                return FormValidation.warning(Messages.HelloWorldBuilder_DescriptorImpl_warnings_reallyFrench());
            }
            return FormValidation.ok();
        }

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return Messages.HelloWorldBuilder_DescriptorImpl_DisplayName();
        }

    }

}
