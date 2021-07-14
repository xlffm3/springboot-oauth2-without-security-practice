package api.oauth.spring.controller;

import api.oauth.spring.dto.OauthDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RestController
public class OAuthController {

    @GetMapping("/afterlogin")
    public ResponseEntity<String> afterlogin(@RequestParam String code) {
        if (Objects.isNull(code)) {
            throw new IllegalStateException("Github 로그인이 실패했습니다.");
        }
        RestTemplate restTemplate = new RestTemplate();
        OauthDto oauthDto = new OauthDto();
        oauthDto.setCode(code);
        oauthDto.setClient_id("7e930566ecaa306c71b5");
        oauthDto.setClient_secret("578679d802bce91380472b3446d4338460f6c06c");
        String accessToken = restTemplate.postForObject("https://github.com/login/oauth/access_token", oauthDto, String.class);
        return ResponseEntity.ok(accessToken);
    }
}
