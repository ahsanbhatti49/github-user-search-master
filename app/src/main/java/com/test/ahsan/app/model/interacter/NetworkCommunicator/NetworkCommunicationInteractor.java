package com.test.ahsan.app.model.interacter.NetworkCommunicator;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.test.ahsan.app.model.dao.GithubUserModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by ahsan on 16/10/2018.
 */

public class NetworkCommunicationInteractor implements NetworkResponseListener {

    private NetworkCommunicationModel finalResponse;
    private String TAG = "VerifyUserImpl";
    private onResponseListener responseListener;
    private String apiName = "", finalParams = "", signature = "";
    private Context context = null;

//    private io.reactivex.Observable<NetworkCommunicationModel> getObservable() {
//        if (!finalParams.equals("") && !signature.equals("")) {
//            NetworkCommunicationModel para = new NetworkCommunicationModel();
//            para.setParams(finalParams);
//            para.setSignature(signature);
//            return RetrofitClient.getRetrofitClient()
//                    .create(NetworkCalls.class)
//                    .getResponse(apiName, para)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread()
//                    );
//        }
//        return null;
//    }
//
//    private DisposableObserver<NetworkCommunicationModel> getObserver() {
//
//        return new DisposableObserver<NetworkCommunicationModel>() {
//            @Override
//            public void onNext(@NonNull NetworkCommunicationModel response) {
//                finalResponse = response;
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                responseListener.onFailure(e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                try {
//                    if ((!finalResponse.equals("")) && responseListener != null) {
//                        new Handler().post(() -> {
//                            // add your code here
//                            try {
//                                // String decStr = decryptString(finalResponse.getParams(), BuildConfig.ENC_DEC_KEY);
//                                responseListener.onSuccess("Sdfsdf");
//                            } catch (Exception e) {
//                                Log.d(TAG, e.getMessage());
//                            }
//                        });
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//    }

    @SuppressLint("HardwareIds")
    @Override
    public void getResponse(Context context, String username, onResponseListener onResponseListener) {
        if (context != null && onResponseListener != null) {
            try {
                this.responseListener = onResponseListener;
                this.context = context;
                new Atask().execute(username);

            } catch (Exception e) {
//               / Log.d(TAG, e.getMessage());
                responseListener.onFailure("Something went wrong please try again");

            }

        }

    }


    //AyncTask to get Single User using API


    class Atask extends AsyncTask<String, Void, Void> {
        private ProgressDialog pDialog;
        boolean apiLimitExceeded = false;
        private GithubUserModel githubUserModel;
        String response = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            pDialog = new ProgressDialog(context.getApplicationContext());
//            pDialog.setMessage("Getting Data ...");
//            pDialog.setIndeterminate(false);
//            pDialog.setCancelable(true);
//            pDialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            HttpURLConnection urlConnection;
            URL url;
            InputStream inputStream;

            //Method 1: To Authorize API access for all HTTP call
            //Uncomment this part of code and input your username and password
//            Authenticator.setDefault(new Authenticator() {
//                @Override
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication("username", "password".toCharArray());
//                }
//            });

            try {

                if (params[0].contains("1234555555555555555")) {
                    String [] temp = params[0].split("1234555555555555555");
                    url = new URL(temp[1]);
                } else {
                    url = new URL("https://api.github.com/users/" + params[0]);
                }
                Log.e("url value", url.toString());
                urlConnection = (HttpURLConnection) url.openConnection();

                //Method 2: To Authorize API access while making HTTP request

                //Uncomment this part of code and input your username and password
//                String basicAuth = "Basic "+Base64.encodeToString("kvipul:password".getBytes(), Base64.DEFAULT).replace("\n", "");
//                urlConnection.setRequestProperty ("Authorization", basicAuth);

                //set request type
                urlConnection.setRequestMethod("GET");

                //if you uncomment the following line GitHub API will not respond
//                urlConnection.setDoOutput(true);

                urlConnection.setDoInput(true);
                urlConnection.connect();
                //check for HTTP response
                int httpStatus = urlConnection.getResponseCode();
                Log.e("httpstatus", "The response is: " + httpStatus);

                //if HTTP response is 200 i.e. HTTP_OK read inputstream else read errorstream
                if (httpStatus != HttpURLConnection.HTTP_OK) {
                    inputStream = urlConnection.getErrorStream();
                    Map<String, List<String>> map = urlConnection.getHeaderFields();
                    System.out.println("Printing Response Header...\n");
                    for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                        System.out.println(entry.getKey()
                                + " : " + entry.getValue());
                    }
                } else {
                    inputStream = urlConnection.getInputStream();
                }

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                while ((temp = bufferedReader.readLine()) != null) {
                    response += temp;
                }
                Log.e("webapi json object", response);


//                if(response.contains("API rate limit exceeded")){
////                    items= new JSONArray();
////                    total_count = "0";
//                    apiLimitExceeded =true;
//                }else {
//                    //convert data string into JSONObject
//                    JSONObject obj = (JSONObject) new JSONTokener(response).nextValue();
//                    items = obj.getJSONArray("items");
//
//                    total_count = obj.getString("total_count");
//                    incomplete_results = obj.getString("incomplete_results");
//                }
                urlConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            if(!apiLimitExceeded){
//                apiLimitError.setVisibility(View.INVISIBLE);
//                setResultListView();
//            }else{
//                repoListView.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, new ArrayList<>()));
//                apiLimitError.setVisibility(View.VISIBLE);
//                count.setText("API rate Limit Error!!Try after some time!");
//            }
            responseListener.onSuccess(response);
            // pDialog.dismiss();
        }
    }


}
