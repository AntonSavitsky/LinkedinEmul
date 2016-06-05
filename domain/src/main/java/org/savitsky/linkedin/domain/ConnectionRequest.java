package org.savitsky.linkedin.domain;

import javax.persistence.*;

/**
 * Created by LenovoUser on 04.06.2016.
 */
@Entity
public class ConnectionRequest {
    @Id
    @GeneratedValue
    private int requestId;

    @ManyToOne
    @JoinColumn(name = "request_from_id")
    private Member requestFrom;

    @ManyToOne
    @JoinColumn(name = "request_to_id")
    private Member requestTo;

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Member getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(Member requestFrom) {
        this.requestFrom = requestFrom;
    }

    public Member getRequestTo() {
        return requestTo;
    }

    public void setRequestTo(Member requestTo) {
        this.requestTo = requestTo;
    }
}
