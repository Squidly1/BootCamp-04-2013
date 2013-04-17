package de.wombatsoftware.TweetCamp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    private static final long serialVersionUID = 4098634670270049365L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date changeTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createTimestamp;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "version")
    private int version = 0;

    @Override
    public boolean equals(Object that) {
	if (this == that) {
	    return true;
	}
	if (that == null) {
	    return false;
	}
	if (getClass() != that.getClass()) {
	    return false;
	}
	if (id != null) {
	    return id.equals(((AbstractEntity) that).id);
	}
	return super.equals(that);
    }

    public Date getChangeTimestamp() {
	return changeTimestamp;
    }

    public Date getCreateTimestamp() {
	return createTimestamp;
    }

    public Long getId() {
	return this.id;
    }

    public int getVersion() {
	return this.version;
    }

    @Override
    public int hashCode() {
	if (id != null) {
	    return id.hashCode();
	}
	return super.hashCode();
    }

    @PrePersist
    public void prePersist() {
	Date date = new Date();
	setCreateTimestamp(date);
	setChangeTimestamp(date);
    }

    @PreUpdate
    public void preUpdate() {
	setChangeTimestamp(new Date());
    }

    public void setChangeTimestamp(Date changeTimestamp) {
	this.changeTimestamp = changeTimestamp;
    }

    protected void setCreateTimestamp(Date createTimestamp) {
	this.createTimestamp = createTimestamp;
    }

    public void setId(final Long id) {
	this.id = id;
    }

    public void setVersion(final int version) {
	this.version = version;
    }

    @Override
    public String toString() {
	String result = getClass().getSimpleName() + " ";
	if (id != null)
	    result += "id: " + id;
	return result;
    }
    
    public boolean isTransient() {
	return id == null;
    }
}