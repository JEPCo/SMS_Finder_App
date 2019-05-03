package com.example.user.databasesystem

class SMSItem {
    var smsId: Int = 0
    var smsPhone: String? = null
    var smsmsg: String? = null

    constructor()
    constructor(sms_id: Int, sms_phone: String, sms_msg: String) {
        this.smsId = sms_id
        this.smsPhone = sms_phone
        this.smsmsg = sms_msg
    }
}