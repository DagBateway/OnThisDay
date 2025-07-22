package com.albertocamillo.onthisday.utils

import java.security.MessageDigest

/**
 * Utility function to generate a unique, deterministic identifier
 * based on the SHA-256 hash of two input strings.
 *
 * Used in the app to create consistent IDs for entities like historical events
 * where no guaranteed unique identifier is provided externally.
 *
 * The resulting ID:
 * - Is the first 16 characters of the hexadecimal-encoded SHA-256 hash.
 * - Has dashes removed and is zero-padded to ensure consistent formatting.
 *
 * @param string1 The first input string (e.g. title or date).
 * @param string2 The second input string (e.g. description or source).
 * @return A 16-character deterministic unique ID string.
 */
fun generateUniqueID(string1: String, string2: String): String {
    // Create SHA-256 hash from the concatenated strings
    val digest = MessageDigest.getInstance("SHA-256")
    val hashBytes = digest.digest("$string1$string2".toByteArray())

    // Convert the byte array to a hexadecimal string
    val hexString = StringBuilder()
    for (byte in hashBytes) {
        // Ensure each byte is converted to a 2-digit hex string
        val hex = (byte.toInt() and 0xFF).toString(16)
        if (hex.length == 1) {
            hexString.append('0') // Pad with leading zero if needed
        }
        hexString.append(hex)
    }

    // Return the first 16 characters of the hex string as the unique ID
    return hexString.substring(0, 16).replace("-", "")
}
