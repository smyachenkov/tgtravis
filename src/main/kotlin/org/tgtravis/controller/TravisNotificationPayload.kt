package org.tgtravis.controller

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class TravisNotificationPayload(
    val id: Int,
    val number: String,
    val type: String,
    val state: String,
    val status: Int,
    val result: Int,
    val status_message: String,
    val result_message: String,
    val started_at: String,
    val finished_at: String,
    val duration: Int,
    val build_url: String,
    val message: String,
    val committed_at: String,
    val author_name: String?,
    val author_email: String?,
    val committer_name: String?,
    val committer_email: String?
)
