package com.atsistemas.lambda.adobesign;

import com.adobe.sign.api.OAuthApi;
import com.adobe.sign.model.oAuth.AuthorizationRequest;
import com.adobe.sign.model.oAuth.Scope;
import com.adobe.sign.utils.ApiException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.util.ArrayList;

public class LambdaAdobeSignPoc {
    private static final String
            SCOPE_TARGET = "user_write",
            SCOPE_MODIFIER = "self",
            CLIENT_ID = "CBJCHBCAABAAC8FlSgcllFUyxnTe1FIRxarHKiCE_V1A",
            REDIRECT_URI = "https://www.adobe.io",
            RESPONSE_TYPE = "code";

    public void myHandler(Context context) {

        final LambdaLogger logger = context.getLogger();
        final OAuthApi oAuthApi = new OAuthApi();

        final ArrayList<Scope> myScopes = new ArrayList() {{
            //Provide the scope type and modifier
            add(new Scope(SCOPE_TARGET, SCOPE_MODIFIER));
        }};

        final AuthorizationRequest authorizationInfo = new AuthorizationRequest(CLIENT_ID, REDIRECT_URI, myScopes, null, RESPONSE_TYPE);
        String authorizationUrl;
        try {
            authorizationUrl = oAuthApi.getAuthorizationUrl(authorizationInfo);
            logger.log("Authorization URL :: " + authorizationUrl);
        } catch (ApiException e) {
            logger.log("Something wrong happened :: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
