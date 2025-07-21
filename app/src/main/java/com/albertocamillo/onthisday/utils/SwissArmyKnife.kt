package com.albertocamillo.onthisday.utils

import java.security.MessageDigest

fun generateUniqueID(string1: String, string2: String): String {

    // Get SHA-256 hash of the combined string
    val digest = MessageDigest.getInstance("SHA-256")
    val hashBytes = digest.digest("$string1$string2".toByteArray())

    // Convert hash bytes to hexadecimal representation
    val hexString = StringBuilder()
    for (byte in hashBytes) {
        // Convert byte to two hexadecimal characters
        val hex = (byte.toInt() and 0xFF).toString(16)
        if (hex.length == 1) {
            hexString.append('0') // Pad single digit with a leading zero
        }
        hexString.append(hex)
    }

    // Take the first 16 characters of the hexadecimal representation and remove the dashes
    return hexString.substring(0, 16).replace("-", "")
}
