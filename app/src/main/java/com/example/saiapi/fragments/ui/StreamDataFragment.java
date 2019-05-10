package com.example.saiapi.fragments.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.saiapi.R;
import com.example.saiapi.utils.constants.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * <p>
 * to handle interaction events.
 * Use the {@link StreamDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StreamDataFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String DEV_EUI = "dev_eui";

    private static final String TAG = "StreamDataFragment";
    // TODO: Rename and change types of parameters
    private String devEui;

    @BindView(R.id.tvStreamData)
    TextView tvStreamData;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param devEui Parameter 2.
     * @return A new instance of fragment StreamDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StreamDataFragment newInstance(String devEui) {
        StreamDataFragment fragment = new StreamDataFragment();
        Bundle args = new Bundle();
        args.putString(DEV_EUI, devEui);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            devEui = getArguments().getString(DEV_EUI);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stream_data, container, false);
        ButterKnife.bind(this, view);
        // Inflate the layout for this fragment

        callData(devEui);

        return view;
    }

    private void callData(String devEui) {
        Call<String> responseBodyCall = ApiClient.getApi().streamJson(Constants.getJwtToken(getContext()), devEui);
        responseBodyCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body());
                } else
                    Log.d(TAG, "onResponse: response not successgfull");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());

            }
        });
//        responseBodyCall.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                if (response.isSuccessful()) {
//                    Log.d(TAG, "server contacted and has file");
////                    tvStreamData.setText(response.body().toString());
////                    new AsyncTask<Void, Void, Void>() {
////
////                        @Override
////                        protected Void doInBackground(Void... voids) {
////                            boolean writtenToDisk = writeResponse(response.body());
////                            return null;
////                        }
////                    };
//                } else {
//                    Log.d(TAG, "server contact failed");
//                }
//            }
//
//            private boolean writeResponse(ResponseBody body) {
//                try {
//                    // todo change the file location/name according to your needs
//                    File futureStudioIconFile = new File(Environment.getExternalStoragePublicDirectory(null) + File.separator + "sample.txt");
//
//                    InputStream inputStream = null;
//                    OutputStream outputStream = null;
//
//                    try {
//                        byte[] fileReader = new byte[4096];
//
//                        long fileSize = body.contentLength();
//                        long fileSizeDownloaded = 0;
//
//                        inputStream = body.byteStream();
//                        outputStream = new FileOutputStream(futureStudioIconFile);
//
//                        while (true) {
//                            int read = inputStream.read(fileReader);
//
//                            if (read == -1) {
//                                break;
//                            }
//
//                            outputStream.write(fileReader, 0, read);
//
//                            fileSizeDownloaded += read;
//
//                            Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
//                        }
//
//                        outputStream.flush();
//
//                        return true;
//                    } catch (IOException e) {
//                        return false;
//                    } finally {
//                        if (inputStream != null) {
//                            inputStream.close();
//                        }
//
//                        if (outputStream != null) {
//                            outputStream.close();
//                        }
//                    }
//                } catch (IOException e) {
//                    return false;
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });

    }


}
