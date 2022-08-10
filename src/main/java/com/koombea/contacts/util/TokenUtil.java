package com.koombea.contacts.util;

import com.auth0.jwt.algorithms.Algorithm;

public class TokenUtil {

    public static final Algorithm ALGORITHM = Algorithm.HMAC256("secret".getBytes()); //just for demonstration purpose
}
