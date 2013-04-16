package de.wombatsoftware.TweetCamp.playground;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class CircularB {
    @Inject
    private CircularA circularA;

    public CircularA getCircularA() {
	return circularA;
    }

    public void setCircularA(CircularA circularA) {
	this.circularA = circularA;
    }
}
