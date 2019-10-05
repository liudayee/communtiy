package com.gt.community.controller;

import com.gt.community.dto.AccessTokenDTO;
import com.gt.community.dto.GithubUser;
import com.gt.community.provider.GithubProdiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthorizeController {
    @Autowired
    private GithubProdiver githubProdiver;

    @Value("${github.client.id}")
    private String clientid;
    @Value("${github.client.secret}")
    private String clientsecret;
    @Value("${github.redirect.uri}")
    private String redirecturi;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientid);
        accessTokenDTO.setClient_secret(clientsecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirecturi);
        accessTokenDTO.setState(state);
        String accessToken = githubProdiver.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProdiver.getUser(accessToken);
        System.err.println(githubUser.getName());
        return "index";
    }


}
