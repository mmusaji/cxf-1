/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.cxf.rs.security.jose;

import java.io.UnsupportedEncodingException;

import org.apache.cxf.common.util.crypto.CryptoUtils;

public final class JoseUtils {
    private JoseUtils() {
        
    }
    
    public static String checkContentType(String contentType, String defaultType) {
        if (contentType != null) {
            int paramIndex = contentType.indexOf(';');
            String typeWithoutParams = paramIndex == -1 ? contentType : contentType.substring(0, paramIndex);
            if (typeWithoutParams.indexOf('/') == -1) {
                contentType = "application/" + contentType;
            }
        } else {
            contentType = defaultType;
        }
        return contentType;
    }
    public static String expandContentType(String contentType) {
        int paramIndex = contentType.indexOf(';');
        String typeWithoutParams = paramIndex == -1 ? contentType : contentType.substring(0, paramIndex);
        if (typeWithoutParams.indexOf('/') == -1) {
            contentType = "application/" + contentType;
        }
        return contentType;
    }
    
    public static String decodeToString(String encoded) {
        try {
            return new String(decode(encoded), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new SecurityException(ex);
        }
        
    }
    public static byte[] decode(String encoded) {
        return CryptoUtils.decodeSequence(encoded);
    }
}
