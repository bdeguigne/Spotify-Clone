package com.example.spotifyclone;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class SingletonDataViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private DataViewModel testViewModel;

    private final Map<Class<? extends ViewModel>, ViewModel> mFactory = new HashMap<>();

    public SingletonDataViewModelFactory(DataViewModel testViewModel) {
        this.testViewModel = testViewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(final @NonNull Class<T> modelClass) {
        mFactory.put(modelClass, testViewModel);

        if (DataViewModel.class.isAssignableFrom(modelClass)) {
            DataViewModel shareVM = null;

            if (mFactory.containsKey(modelClass)) {
                shareVM = (DataViewModel) mFactory.get(modelClass);
            } else {
                try {
                    shareVM = (DataViewModel) modelClass.getConstructor(Runnable.class).newInstance(new Runnable() {
                        @Override
                        public void run() {
                            mFactory.remove(modelClass);
                        }
                    });
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException("Cannot create an instance of " + modelClass, e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Cannot create an instance of " + modelClass, e);
                } catch (InstantiationException e) {
                    throw new RuntimeException("Cannot create an instance of " + modelClass, e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException("Cannot create an instance of " + modelClass, e);
                }
                mFactory.put(modelClass, shareVM);
            }

            return (T) shareVM;
        }
        return super.create(modelClass);
    }
}
