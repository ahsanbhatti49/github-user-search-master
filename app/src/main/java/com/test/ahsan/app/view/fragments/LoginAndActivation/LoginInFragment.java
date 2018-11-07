//package com.finja.abl.wallet.view.fragments.LoginAndActivation;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v4.app.Fragment;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//
//import com.finja.abl.wallet.R;
//import com.finja.abl.wallet.model.dao.VerifyUserModel;
//import com.finja.abl.wallet.presenter.LoginAndRegistration.implementators.BaseImplementator;
//import com.finja.abl.wallet.utils.AppConstants;
//import com.finja.abl.wallet.utils.NotificationUtil;
//import com.finja.abl.wallet.utils.EmptyUtil;
//import com.finja.abl.wallet.utils.EventBussResponse;
//import com.finja.abl.wallet.view.viewContracter.loginAndRegistration.BaseNetworkContractor;
//import com.google.gson.Gson;
//
//import org.greenrobot.eventbus.EventBus;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import butterknife.Unbinder;
//
//
//public class LoginInFragment extends Fragment implements BaseNetworkContractor {
//
//    Unbinder unbinder;
//    @BindView(R.id.etMobileNumber)
//    EditText etMobileNumber;
//
//    @BindView(R.id.etCNIC)
//    EditText etCNIC;
//    @BindView(R.id.btnSubmit)
//    Button btnSubmit;
//
//    private BaseImplementator baseImplementator;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.login_fragment2, container, false);
//        unbinder = ButterKnife.bind(this, view);
//        initMVP();
//        initListener();
//        return view;
//    }
//
//    private void initListener() {
//        etCNIC.addTextChangedListener(new TextWatcher() {
//            int prevL = 0;
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                prevL = s.length();
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                int length = s.length();
//                if ((prevL < length) && (length == 5 || length == 13)) {
//                    String data = etCNIC.getText().toString();
//                    etCNIC.setText(data + "-");
//                    etCNIC.setSelection(length + 1);
//                }
//
//            }
//        });
//    }
//
//    private void initMVP() {
//        baseImplementator = new BaseImplementator(this);
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//        if (baseImplementator != null) {
//            baseImplementator = null;
//        }
//    }
//
//    @OnClick({R.id.btnSubmit})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.btnSubmit:
//                showProgress();
//                if (EmptyUtil.ifValidCnic(etCNIC) && EmptyUtil.ifValidMobileNo(etMobileNumber)) {
//                    btnSubmit.setEnabled(false);
//                    JSONObject initParams = new JSONObject();
//                    try {
//                        initParams.put(AppConstants.MOBILE_NO, etMobileNumber.getText().toString());
//                        initParams.put(AppConstants.CNIC, etCNIC.getText().toString());
//                        baseImplementator.getApiResponse(getActivity(), initParams, getString(R.string.stringVerifyDuplicate));
//                    } catch (JSONException e) {
//                        hideProgress();
//                        e.printStackTrace();
//                    }
//
//                } else {
//                    hideProgress();
//                    return;
//                }
//                break;
// //            case R.id.etMobileNetwork:
////                PopupMenu dropDownMenu = new PopupMenu(getActivity(), etMobileNetwork);
////                dropDownMenu.getMenuInflater().inflate(R.menu.networks, dropDownMenu.getMenu());
////                dropDownMenu.setOnMenuItemClickListener(menuItem -> {
////                    etMobileNetwork.setText(menuItem.getTitle());
////                    return true;
////                });
////                dropDownMenu.show();
////                break;
//        }
//    }
//
//    @Override
//    public void showProgress() {
//        btnSubmit.setEnabled(true);
//        NotificationUtil.getInstance().showLoading(getActivity());
//    }
//
//    @Override
//    public void hideProgress() {
//        btnSubmit.setEnabled(true);
//        NotificationUtil.getInstance().dismissLoading();
//    }
//
//    @Override
//    public void onError(String errorMsg) {
//        btnSubmit.setEnabled(true);
//        NotificationUtil.getInstance().showErrorAlert(getActivity(), getString(R.string.stringBankingError), errorMsg);
//    }
//
//    @Override
//    public void displayResult(String response) {
//        //List<Data> items = new Gson().fromJson(result, new TypeToken<List<Data>>() {}.getType());
//        btnSubmit.setEnabled(true);
//        try {
//            Gson gson = new Gson();
//            VerifyUserModel verifyUserModel = gson.fromJson(response, VerifyUserModel.class);
//
//            if (verifyUserModel.getStatus().equals(AppConstants.SUCCESS_CODE)) {
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("otp", verifyUserModel.getData().getOtp());
//                bundle.putString(AppConstants.CNIC, etCNIC.getText().toString());
//                bundle.putString(AppConstants.MOBILE_NO, etMobileNumber.getText().toString());
//                EventBus.getDefault()
//                        .post(new EventBussResponse(AppConstants.GOTO_FRAGMENT,
//                                new Intent().putExtra(AppConstants.TAG, getString(R.string.stringVerifyOtp)), bundle,
//                                new VerifyOtpFragment()
//                        ));
//            }else{
//                NotificationUtil.getInstance().showErrorAlert(getActivity(),getString(R.string.stringBankingError),verifyUserModel.getMsg());
//            }
//        } catch (Exception e) {
//            Log.d("LoginFragment", e.getMessage());
//        }
//    }
//}
