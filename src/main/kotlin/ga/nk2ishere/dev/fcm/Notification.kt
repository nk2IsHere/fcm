package ga.nk2ishere.dev.fcm

/**
 * Created by nk2 on 16-Sep-17.
 */

class Notification(title: String) {
    var title: String? = null
    var body: String? = null
    var sound: String? = null
    var icon: String? = null
    var tag: String? = null
    var color: String? = null
    var badge: String? = null
    var clickAction: String? = null
    var titleLocKey: String? = null
    var titleLocArgs: List<String>? = null
    var bodyLocKey: String? = null
    var bodyLockArgs: List<String>? = null

    constructor(title: String, body: String) : this(title) {
        this.title = title
        this.body = body
    }
}
