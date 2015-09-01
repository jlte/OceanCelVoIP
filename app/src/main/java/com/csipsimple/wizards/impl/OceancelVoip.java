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

import java.util.HashMap;

import com.csipsimple.R;
import com.csipsimple.api.SipConfigManager;
import com.csipsimple.api.SipProfile;
import com.csipsimple.utils.PreferencesWrapper;

public class OceancelVoip extends SimpleImplementation {


    @Override
    protected String getDomain() {
        return "192.168.7.49:5060";
    }

    @Override
    protected String getDefaultName() {
        return "Oceancel";
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
        accountUsername.setTitle("8 digits user account");
        accountUsername.getEditText().setInputType(InputType.TYPE_CLASS_PHONE);
        //hidePreference(null, SERVER);

    }
    @Override
    public String getDefaultFieldSummary(String fieldName) {
            if(fieldName.equals(USER_NAME)) {
                return parent.getString(R.string.w_sapo_phone_number_desc);
            }
            return super.getDefaultFieldSummary(fieldName);
    }

    @Override
    public SipProfile buildAccount(SipProfile account) {
        account.display_name = accountDisplayName.getText().trim();
        account.acc_id = accountUsername.getText().trim()+" <sip:"+ accountUsername.getText().trim() + "@192.168.7.49t:5060>";

        account.reg_uri = "sip:192.168.7.49:5060";
        account.proxies = new String[] { "sip:192.168.7.49:5060" } ;


        account.realm = "*";
        account.username = getText(accountUsername).trim();
        account.data = getText(accountPassword);
        account.scheme = SipProfile.CRED_SCHEME_DIGEST;
        account.datatype = SipProfile.CRED_DATA_PLAIN_PASSWD;

        account.reg_timeout = 1800;

        if(canTcp()) {
            account.transport = accountUseTcp.isChecked() ? SipProfile.TRANSPORT_TCP : SipProfile.TRANSPORT_UDP;
        }else {
            account.transport = SipProfile.TRANSPORT_UDP;
        }

        return account;
    }
    @Override
    public void setDefaultParams(PreferencesWrapper prefs) {
        super.setDefaultParams(prefs);
        // Add stun server
        prefs.setPreferenceBooleanValue(SipConfigManager.ENABLE_STUN, false);
        prefs.setPreferenceBooleanValue(SipConfigManager.DISABLE_RPORT, false);
        prefs.setPreferenceBooleanValue(SipConfigManager.ENABLE_DNS_SRV, false);

    }
}