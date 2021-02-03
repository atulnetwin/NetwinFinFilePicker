package com.netwinfin.filepickerlib;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.netwinfin.filepickerlib.models.Config;

import java.lang.ref.WeakReference;


/* This is the configuration class for File Picker */
public final class FilePicker {
    
    private final WeakReference<Activity> mActivity;
    private final WeakReference<Fragment> mContext;

    private FilePicker(Activity activity){
        this(activity, null);
    }

    private FilePicker(Fragment fragment){
        this(fragment.getActivity(), fragment);
    }

    public FilePicker(Activity activity, Fragment fragment) {
        this.mActivity = new WeakReference<>(activity);
        this.mContext = new WeakReference<>(fragment);
    }

    /**
     * Start UnicornFilePicker from an activity
     *
     * @param activity Activity instance
     * @return UnicornFilePicker instance
     */
    public static FilePicker from(Activity activity){
        return new FilePicker(activity);
    }

    /**
     * Start UnicornFilePicker from a fragment
     *
     * @param fragment Fragment instance
     * @return UnicornFilePicker instance
     */
    public static FilePicker from(Fragment fragment){
        return new FilePicker(fragment);
    }

    /**
     * Start FilePicker activity and wait for result
     * @param requestCode Integer identity for Activity or Fragment request
     */
    public void forResult(int requestCode){
        Config.getInstance().setReqCode(requestCode);

        Activity activity = getActivity();
        if(activity==null){
            return;
        }

        Intent intent = new Intent(activity, FilePickerActivity.class);

        Fragment fragment = getFragment();
        if(fragment==null){
            activity.startActivityForResult(intent, requestCode);
        }else{
            fragment.startActivityForResult(intent, requestCode);
        }
    }

    public ConfigBuilder addConfigBuilder(){
        return new ConfigBuilder(this);
    }


    @Nullable
    Activity getActivity(){
        return mActivity.get();
    }

    @Nullable
    Fragment getFragment(){
        return mContext.get();
    }

}
