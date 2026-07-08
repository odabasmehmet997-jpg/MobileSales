package org.springframework.http;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.FrameMetricsAggregator;
import com.google.android.material.card.MaterialCardViewHelper;


/* loaded from: classes.dex */
public enum HttpStatus {
    CONTINUE(100, "Continue"),
    SWITCHING_PROTOCOLS(101, "Switching Protocols"),
    PROCESSING(102, "Processing"),
    CHECKPOINT(103, "Checkpoint"),
    OK(200, "OK"),
    CREATED(201, "Created"),
    ACCEPTED(202, "Accepted"),
    NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),
    NO_CONTENT(204, "No Content"),
    RESET_CONTENT(205, "Reset Content"),
    PARTIAL_CONTENT(206, "Partial Content"),
    MULTI_STATUS(207, "Multi-Status"),
    ALREADY_REPORTED(208, "Already Reported"),
    IM_USED(226, "IM Used"),
    MULTIPLE_CHOICES(MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION, "Multiple Choices"),
    MOVED_PERMANENTLY(301, "Moved Permanently"),
    FOUND(302, "Found"),
    MOVED_TEMPORARILY(302, "Moved Temporarily"),
    SEE_OTHER(303, "See Other"),
    NOT_MODIFIED(304, "Not Modified"),
    USE_PROXY(305, "Use Proxy"),
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),
    PERMANENT_REDIRECT(308, "Permanent Redirect"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(TypedValues.CycleType.TYPE_CURVE_FIT, "Unauthorized"),
    PAYMENT_REQUIRED(TypedValues.CycleType.TYPE_VISIBILITY, "Payment Required"),
    FORBIDDEN(TypedValues.CycleType.TYPE_ALPHA, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
    REQUEST_TIMEOUT(408, "Request Timeout"),
    CONFLICT(409, "Conflict"),
    GONE(410, "Gone"),
    LENGTH_REQUIRED(411, "Length Required"),
    PRECONDITION_FAILED(412, "Precondition Failed"),
    PAYLOAD_TOO_LARGE(413, "Payload Too Large"),
    REQUEST_ENTITY_TOO_LARGE(413, "Request Entity Too Large"),
    URI_TOO_LONG(414, "URI Too Long"),
    REQUEST_URI_TOO_LONG(414, "Request-URI Too Long"),
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
    REQUESTED_RANGE_NOT_SATISFIABLE(TypedValues.CycleType.TYPE_PATH_ROTATE, "Requested range not satisfiable"),
    EXPECTATION_FAILED(417, "Expectation Failed"),
    I_AM_A_TEAPOT(418, "I'm a teapot"),
    INSUFFICIENT_SPACE_ON_RESOURCE(419, "Insufficient Space On Resource"),
    METHOD_FAILURE(TypedValues.CycleType.TYPE_EASING, "Method Failure"),
    DESTINATION_LOCKED(421, "Destination Locked"),
    UNPROCESSABLE_ENTITY(TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, "Unprocessable Entity"),
    LOCKED(TypedValues.CycleType.TYPE_WAVE_PERIOD, "Locked"),
    FAILED_DEPENDENCY(TypedValues.CycleType.TYPE_WAVE_OFFSET, "Failed Dependency"),
    UPGRADE_REQUIRED(426, "Upgrade Required"),
    PRECONDITION_REQUIRED(428, "Precondition Required"),
    TOO_MANY_REQUESTS(429, "Too Many Requests"),
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(TypedValues.PositionType.TYPE_TRANSITION_EASING, "Not Implemented"),
    BAD_GATEWAY(TypedValues.PositionType.TYPE_DRAWPATH, "Bad Gateway"),
    SERVICE_UNAVAILABLE(TypedValues.PositionType.TYPE_PERCENT_WIDTH, "Service Unavailable"),
    GATEWAY_TIMEOUT(TypedValues.PositionType.TYPE_PERCENT_HEIGHT, "Gateway Timeout"),
    HTTP_VERSION_NOT_SUPPORTED(TypedValues.PositionType.TYPE_SIZE_PERCENT, "HTTP Version not supported"),
    VARIANT_ALSO_NEGOTIATES(TypedValues.PositionType.TYPE_PERCENT_X, "Variant Also Negotiates"),
    INSUFFICIENT_STORAGE(TypedValues.PositionType.TYPE_PERCENT_Y, "Insufficient Storage"),
    LOOP_DETECTED(TypedValues.PositionType.TYPE_CURVE_FIT, "Loop Detected"),
    BANDWIDTH_LIMIT_EXCEEDED(509, "Bandwidth Limit Exceeded"),
    NOT_EXTENDED(TypedValues.PositionType.TYPE_POSITION_TYPE, "Not Extended"),
    NETWORK_AUTHENTICATION_REQUIRED(FrameMetricsAggregator.EVERY_DURATION, "Network Authentication Required");

    private final String reasonPhrase;
    private final int value;

    HttpStatus(final int i2, final String str) {
        value = i2;
        reasonPhrase = str;
    }

    public int value() {
        return value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public boolean is1xxInformational() {
        return Series.INFORMATIONAL == this.series();
    }

    public boolean is2xxSuccessful() {
        return Series.SUCCESSFUL == this.series();
    }

    public boolean is3xxRedirection() {
        return Series.REDIRECTION == this.series();
    }

    public boolean is4xxClientError() {
        return Series.CLIENT_ERROR == this.series();
    }

    public boolean is5xxServerError() {
        return Series.SERVER_ERROR == this.series();
    }

    public Series series() {
        return Series.valueOf(this);
    }

    @Override // java.lang.Enum
    public String toString() {
        return Integer.toString(value);
    }

    public static HttpStatus valueOf(final int i2) {
        for (final HttpStatus httpStatus : HttpStatus.values()) {
            if (httpStatus.value == i2) {
                return httpStatus;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + i2 + "]");
    }

    public enum Series {
        INFORMATIONAL(1),
        SUCCESSFUL(2),
        REDIRECTION(3),
        CLIENT_ERROR(4),
        SERVER_ERROR(5);

        private final int value;

        Series(final int i2) {
            value = i2;
        }

        public int value() {
            return value;
        }

        public static Series valueOf(final int i2) {
            final int i3 = i2 / 100;
            for (final Series series : Series.values()) {
                if (series.value == i3) {
                    return series;
                }
            }
            throw new IllegalArgumentException("No matching constant for [" + i2 + "]");
        }

        public static Series valueOf(final HttpStatus httpStatus) {
            return Series.valueOf(httpStatus.value);
        }
    }
}
