package ga.nk2ishere.dev.fcm

/**
* Created by nk2 on 16-Sep-17.
*/

import java.util.ArrayList
import java.util.HashMap

class Message private constructor(builder: MessageBuilder) {
    private val to: String?
    private val condition: String?

    private val collapse_key: String?
    private val priority: String?
    private val content_available: Boolean?
    private val time_to_live: Long?
    private val restricted_package_name: String?
    private val dry_run: Boolean?
    private val data: Map<String, Any>?
    private val registration_ids: List<String>?
    private val notification: Notification?

    init {
        this.to = builder.to
        this.collapse_key = builder.collapse_key
        this.priority = builder.priority
        this.content_available = builder.content_available
        this.time_to_live = builder.time_to_live
        this.restricted_package_name = builder.restricted_package_name
        this.dry_run = builder.dry_run
        this.data = builder.data
        this.notification = builder.notification
        this.condition = builder.condition
        this.registration_ids = builder.registration_ids
    }


    object Priority {
        internal fun isValid(priority: String): Boolean = (priority == "normal") or (priority == "high")
    }

    class MessageBuilder {
        internal var to: String? = null
        internal var condition: String? = null // field to must be null if condition is set

        internal var collapse_key: String? = null
        internal var priority: String? = null
        internal var time_to_live: Long? = null
        internal var restricted_package_name: String? = null
        internal var content_available: Boolean? = null
        internal var dry_run: Boolean? = null
        internal var data: MutableMap<String, Any>? = HashMap(2)
        internal var registration_ids: MutableList<String>? = ArrayList(2)
        internal var notification: Notification? = null

        fun toTopic(topic: String): MessageBuilder {
            this.to = topic
            return this
        }

        fun toToken(toToken: String): MessageBuilder {
            this.to = toToken
            return this
        }

        fun toCondition(condition: String): MessageBuilder {
            this.condition = condition
            this.to = null
            return this
        }

        fun addRegistrationToken(registrationTokens: List<String>): MessageBuilder {
            this.registration_ids!!.addAll(registrationTokens)
            this.to = null
            return this
        }

        fun addRegistrationToken(registrationToken: String): MessageBuilder {
            this.registration_ids!!.add(registrationToken)
            this.to = null
            return this
        }

        fun collapseKey(collapse_key: String): MessageBuilder {
            this.collapse_key = collapse_key
            return this
        }

        fun priority(priority: String): MessageBuilder {
            if (Priority.isValid(priority))
                this.priority = priority
            else
                this.priority = "normal"
            return this
        }

        fun contentAvailable(content_available: Boolean): MessageBuilder {
            this.content_available = content_available
            return this
        }

        fun timeToLive(time_to_live: Long): MessageBuilder {
            this.time_to_live = time_to_live
            return this
        }

        fun restrictedPackageName(restrictedPackageName: String): MessageBuilder {
            this.restricted_package_name = restrictedPackageName
            return this
        }

        fun dryRun(dryRun: Boolean): MessageBuilder {
            this.dry_run = dryRun
            return this
        }

        fun addData(data: Map<String, Any>): MessageBuilder {
            this.data!!.putAll(data)
            return this
        }

        fun addData(key: String, value: Any): MessageBuilder {
            this.data!!.put(key, value)
            return this
        }

        fun notification(notification: Notification): MessageBuilder {
            this.notification = notification
            return this
        }

        fun build(): Message {
            removeEmptyCollections()
            return Message(this)
        }

        private fun removeEmptyCollections() {
            if (this.data != null && this.data!!.isEmpty())
                this.data = null
            if (this.registration_ids != null && this.registration_ids!!.isEmpty())
                this.registration_ids = null
        }
    }
}
