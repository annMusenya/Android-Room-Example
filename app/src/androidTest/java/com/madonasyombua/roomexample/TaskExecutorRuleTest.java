package com.madonasyombua.roomexample;

import android.arch.core.executor.ArchTaskExecutor;
import android.arch.core.executor.TaskExecutor;
import android.support.annotation.NonNull;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class TaskExecutorRuleTest extends TestWatcher {
    @Override
    protected void starting(Description description) {
        super.starting(description);

        ArchTaskExecutor.getInstance().setDelegate(new TaskExecutor() {
            @Override
            public void executeOnDiskIO(@NonNull Runnable runnable) {

            }

            @Override
            public void postToMainThread(@NonNull Runnable runnable) {

            }

            @Override
            public boolean isMainThread() {
                return true;
            }
        });
    }

    @Override
    protected void finished(Description description) {
        super.finished(description);
        ArchTaskExecutor.getInstance().setDelegate(null);
    }
}
