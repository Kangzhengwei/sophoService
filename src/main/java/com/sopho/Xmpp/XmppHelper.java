package com.sopho.Xmpp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.sopho.entity.User;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jxmpp.jid.parts.Localpart;
import org.jxmpp.stringprep.XmppStringprepException;


/**
 * <b>function:</b> 利用Smack框架完成 XMPP 协议通信
 *
 * @author kzw
 * @version 1.0
 * @createDate 2019-4-22 上午10:28:18
 * @file XmppHelper.java
 * @package com.sopho.Xmpp
 * @project sophoservice
 */
public class XmppHelper {

    private String server = "192.9.201.73";
    private AbstractXMPPConnection connection;
    public static XmppHelper helper;


    public static XmppHelper getInstance() {
        if (helper == null) {
            helper = new XmppHelper();
        }
        return helper;
    }

    private XmppHelper() {
        try {
            XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    .setXmppDomain(server)
                    .setSecurityMode(XMPPTCPConnectionConfiguration.SecurityMode.disabled)
                    .setHost(server)
                    .setPort(5222)
                    .build();
            connection = new XMPPTCPConnection(config);
            connection.connect();
        } catch (SmackException | IOException | XMPPException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean create(User user) {
        if (connection == null || !connection.isConnected()) {
            return false;
        }
        try {
            AccountManager accountManager = AccountManager.getInstance(connection);
            if (accountManager.supportsAccountCreation()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", user.getNickName());
                accountManager.sensitiveOperationOverInsecureConnection(true);
                accountManager.createAccount(Localpart.from(user.getPhoneNumber()), user.getPassWord(), map);
                return true;
            }
        } catch (SmackException.NoResponseException | XMPPException.XMPPErrorException | SmackException.NotConnectedException | InterruptedException | XmppStringprepException e) {
            e.printStackTrace();
        }
        return false;
    }
}