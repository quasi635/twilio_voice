package com.twilio.twilio_voice.service

import android.content.Context

/**
 * Resolves a call the system dialer placed through this app's phone account, which arrives with
 * only the dialed number. Set on [TVConnectionService.outgoingCallResolver], typically from the
 * host app's Application.onCreate.
 */
interface TVOutgoingCallResolver {
    /**
     * Called on the main thread. Must call exactly one of [Callback.onResolved] or
     * [Callback.onFailed], from any thread.
     * @param number The dialed number, as the dialer formatted it.
     */
    fun resolve(context: Context, number: String, callback: Callback)

    interface Callback {
        fun onResolved(call: TVResolvedCall)
        fun onFailed(reason: String)
    }
}

/**
 * @param token Twilio access token for the call.
 * @param from Client identity placing the call.
 * @param to Normalised destination passed to the TwiML application.
 * @param params Extra custom parameters for the TwiML application.
 */
data class TVResolvedCall(
    val token: String,
    val from: String,
    val to: String,
    val params: Map<String, String> = emptyMap(),
)
