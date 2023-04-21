package com.farid.artemis.domain.biz.token;

import lombok.Data;

/**
 * @author falcon
 * @since 2022/9/16
 */
@Data
public class TokenRequest {

    private String grant_type;

    private String client_id;

    private String client_secret;

    private String scope;
}
