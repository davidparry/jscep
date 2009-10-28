/*
 * Copyright (c) 2009 David Grant
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.google.code.jscep.response;

import com.google.code.jscep.asn1.PkiStatus;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedDataParser;

import java.security.GeneralSecurityException;
import java.security.cert.CertStore;

public class CertRep implements ScepResponse {
    private String transId;
    private int msgType;
    private final CMSSignedDataParser parser;

    public CertRep(CMSSignedDataParser parser) {
        this.parser = parser;
    }

    public void setTransactionId(String transId) {
        this.transId = transId;
    }

    public String getTransactionId() {
        return transId;
    }

    public int getMessageType() {
        return msgType;
    }

    public void setMessageType(int msgType) {
        this.msgType = msgType;
    }

    public byte[] getRecipientNonce() {
        return new byte[16];
    }

    public byte[] getSenderNonce() {
        return new byte[16];
    }

    public int getPkiStatus() {
        return PkiStatus.FAILURE;
    }

    public CertStore getCRL() throws GeneralSecurityException, CMSException {
        return parser.getCertificatesAndCRLs("Collection", "BC");
    }
}