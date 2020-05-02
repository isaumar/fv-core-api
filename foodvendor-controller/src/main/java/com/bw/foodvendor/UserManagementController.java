package com.bw.foodvendor;

import com.bw.foodvendor.configuration.Auth;
import com.bw.foodvendor.dto.LoginRequestDto;
import com.bw.foodvendor.dto.RegisterRequestDto;
import com.bw.foodvendor.entity.PortalUser;
import com.bw.foodvendor.service.UserManagementService;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class UserManagementController {

    @Value("${server.url}")
    private String appUrl;

    @Value("${security.jwt.client-id}")
    private String clientUsername;

    @Value("${security.jwt.password}")
    private String clientPassword;

    @Autowired
    private UserManagementService userManagementService;
    @Autowired
    private Auth auth;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/login")
    public ResponseEntity login (@Valid @RequestBody LoginRequestDto request)
            throws IOException, AuthenticationException {


        CloseableHttpClient client = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(appUrl + "/oauth/token");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "password"));
        params.add(new BasicNameValuePair("username", request.getEmail()));
        params.add(new BasicNameValuePair("password", request.getPassword()));
        httpPost.setEntity(new UrlEncodedFormEntity(params));


        UsernamePasswordCredentials credentials =
                new UsernamePasswordCredentials(clientUsername, clientPassword);


        httpPost.addHeader(new BasicScheme().authenticate(credentials, httpPost, null));

        try {
            ResponseHandler<Map<String,Object>> handler = (response) -> {
                int statusCode = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                Map<String,Object> map = new HashMap<>();
                map.put("status", statusCode);
                map.put("content", Json.parse(EntityUtils.toString(entity)));
                return map;
            };
            Map<String,Object> response = client.execute(httpPost, handler);
            client.close();

            int status = (Integer)response.get("status");
            return new ResponseEntity<>(response.get("content"), HttpStatus.valueOf(status));
        } finally {
        }

    }

    @PostMapping("/register")
    public ResponseEntity<?> register (@Valid @RequestBody RegisterRequestDto request) {
       PortalUser user =  userManagementService.register(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/testRole")
    @PreAuthorize("hasRole('ROLE_VENDOR')")
    public String test() {
        return "WORKS";
    }
}
