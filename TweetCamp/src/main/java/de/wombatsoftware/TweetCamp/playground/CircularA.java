package de.wombatsoftware.TweetCamp.playground;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class CircularA {
    @Inject
    private CircularB circularB;

    public CircularB getCircularB() {
	return circularB;
    }

    public void setCircularB(CircularB circularB) {
	this.circularB = circularB;
    }
}