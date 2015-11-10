/**
 * Copyright (C) 2010-2012 Regis Montoya (aka r3gis - www.r3gis.fr)
 * This file is part of CSipSimple.
 *
 *  CSipSimple is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  If you own a pjsip commercial license you can also redistribute it
 *  and/or modify it under the terms of the GNU Lesser General Public License
 *  as an android library.
 *
 *  CSipSimple is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with CSipSimple.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.csipsimple.wizards.impl;

import android.text.InputType;
import android.widget.Toast;

import java.util.HashMap;

import com.csipsimple.R;
import com.csipsimple.api.SipConfigManager;
import com.csipsimple.api.SipProfile;
import com.csipsimple.utils.Log;
import com.csipsimple.utils.PreferencesWrapper;

public class OceancelVoip extends SimpleImplementation {
    private static final String TAG = "JRCk";
    private static final String SRV = "192.168.8.249";
    private static final String SRV_PORT =  SRV + ":5060";
    private static final String SIP_SRV_PORT = "sip:" + SRV + ":5060";

    @Override
    protected String getDomain() {
        return SRV_PORT + ":5060";
    }

    @Override
    protected String getDefaultName() {
        return "OceanCel"; /*return "OceanCel";*/
    }


    //Customization
    @Override
    public void fillLayout(final SipProfile account) {
        super.fillLayout(account);
/*
        accountUsername.setTitle(R.string.w_common_phone_number);
        accountUsername.setDialogTitle(R.string.w_common_phone_number);
        accountUsername.getEditText().setInputType(InputType.TYPE_CLASS_PHONE);
*/
        accountUsername.setTitle("7 digits user number");
        accountUsername.getEditText().setInputType(InputType.TYPE_CLASS_PHONE);
        //hidePreference(null, SERVER);
        Log.d(TAG, "called3!!!");

    }
    @Override
    public String getDefaultFieldSummary(String fieldName) {
//            if(fieldName.equals(USER_NAME)) {
//                return parent.getString(R.string.w_sapo_phone_number_desc);
//            }
            return super.getDefaultFieldSummary(fieldName);
    }

    @Override
    public SipProfile buildAccount(SipProfile account) {
        account.display_name = accountDisplayName.getText().trim();
        account.acc_id = accountUsername.getText().trim()+" <sip:"+ accountUsername.getText().trim() + "@" + SRV +"t:5060>";

        account.reg_uri = SIP_SRV_PORT;
        account.proxies = new String[] { SIP_SRV_PORT } ;


        account.realm = "*";
        account.username = getText(accountUsername).trim();
        account.data = getText(accountPassword);
        account.scheme = SipProfile.CRED_SCHEME_DIGEST;
        account.datatype = SipProfile.CRED_DATA_PLAIN_PASSWD;

        account.reg_timeout = 1800;

//        if(canTcp()) {
//            account.transport = accountUseTcp.isChecked() ? SipProfile.TRANSPORT_TCP : SipProfile.TRANSPORT_UDP;
//        }else {
            account.transport = SipProfile.TRANSPORT_UDP;
//        }
        Log.d(TAG, "called2!!!");

        return account;
    }
    @Override
    public void setDefaultParams(PreferencesWrapper prefs) {
        super.setDefaultParams(prefs);
        // Add stun server
        prefs.setPreferenceBooleanValue(SipConfigManager.ENABLE_STUN, false);
        prefs.setPreferenceBooleanValue(SipConfigManager.DISABLE_RPORT, false);
        prefs.setPreferenceBooleanValue(SipConfigManager.ENABLE_DNS_SRV, false);

        prefs.setPreferenceBooleanValue(SipConfigManager.ENABLE_TLS, false);

//        prefs.setPreferenceBooleanValue(SipConfigManager.CODECS_PER_BANDWIDTH, true);

        prefs.setPreferenceBooleanValue(SipConfigManager.PREVENT_SCREEN_ROTATION ,true);
        prefs.setPreferenceBooleanValue(SipConfigManager.USE_SOFT_VOLUME , true);

                prefs.setCodecPriority("PCMU/8000/1", SipConfigManager.CODEC_WB, "200");
        prefs.setCodecPriority("G722/16000/1", SipConfigManager.CODEC_WB,"199");

/*
        prefs.setCodecPriority("PCMA/8000/1",  SipConfigManager.CODEC_WB,"0");
        prefs.setCodecPriority("G722/16000/1", SipConfigManager.CODEC_WB,"201");
        prefs.setCodecPriority("G729/8000/1",  SipConfigManager.CODEC_WB,"0");
        prefs.setCodecPriority("iLBC/8000/1",  SipConfigManager.CODEC_WB,"0");
        prefs.setCodecPriority("speex/8000/1", SipConfigManager.CODEC_WB,"0");
        prefs.setCodecPriority("speex/16000/1", SipConfigManager.CODEC_WB,"0");
        prefs.setCodecPriority("speex/32000/1", SipConfigManager.CODEC_WB,"0");
        prefs.setCodecPriority("GSM/8000/1",   SipConfigManager.CODEC_WB, "0");
        prefs.setCodecPriority("SILK/8000/1",  SipConfigManager.CODEC_WB, "0");
        prefs.setCodecPriority("SILK/12000/1", SipConfigManager.CODEC_WB, "0");
        prefs.setCodecPriority("SILK/16000/1", SipConfigManager.CODEC_WB, "0");
        prefs.setCodecPriority("SILK/24000/1", SipConfigManager.CODEC_WB, "0");
        prefs.setCodecPriority("G726-16/8000/1", SipConfigManager.CODEC_WB, "0");
        prefs.setCodecPriority("G726-24/8000/1", SipConfigManager.CODEC_WB, "0");
        prefs.setCodecPriority("G726-32/8000/1", SipConfigManager.CODEC_WB, "0");
        prefs.setCodecPriority("G726-40/8000/1", SipConfigManager.CODEC_WB, "0");
        prefs.setCodecPriority("opus/48000/1", SipConfigManager.CODEC_WB, "0");
*/

         // G.729, G723.1, GSM, iLBC
        prefs.setCodecPriority("PCMU/8000/1",  SipConfigManager.CODEC_NB,"200");
        prefs.setCodecPriority("G722/16000/1", SipConfigManager.CODEC_NB,"199");
/*
        prefs.setCodecPriority("PCMA/8000/1",  SipConfigManager.CODEC_NB,"0");
        prefs.setCodecPriority("G722/16000/1", SipConfigManager.CODEC_NB,"201");
        prefs.setCodecPriority("G729/8000/1",  SipConfigManager.CODEC_NB,"0");
        prefs.setCodecPriority("iLBC/8000/1",  SipConfigManager.CODEC_NB,"0");
        prefs.setCodecPriority("speex/8000/1", SipConfigManager.CODEC_NB,"0");
        prefs.setCodecPriority("speex/16000/1", SipConfigManager.CODEC_NB,"0");
        prefs.setCodecPriority("speex/32000/1", SipConfigManager.CODEC_NB,"0");
        prefs.setCodecPriority("GSM/8000/1",   SipConfigManager.CODEC_NB, "0");
        prefs.setCodecPriority("SILK/8000/1",  SipConfigManager.CODEC_NB, "0");
        prefs.setCodecPriority("SILK/12000/1", SipConfigManager.CODEC_NB, "0");
        prefs.setCodecPriority("SILK/16000/1", SipConfigManager.CODEC_NB, "0");
        prefs.setCodecPriority("SILK/24000/1", SipConfigManager.CODEC_NB, "0");
        prefs.setCodecPriority("G726-16/8000/1", SipConfigManager.CODEC_NB, "0");
        prefs.setCodecPriority("G726-24/8000/1", SipConfigManager.CODEC_NB, "0");
        prefs.setCodecPriority("G726-32/8000/1", SipConfigManager.CODEC_NB, "0");
        prefs.setCodecPriority("G726-40/8000/1", SipConfigManager.CODEC_NB, "0");
        prefs.setCodecPriority("opus/48000/1", SipConfigManager.CODEC_NB, "0");
*/

        Log.d(TAG, "called!!!");

    }
    @Override
    public boolean needRestart() {
        return true;
    }
}