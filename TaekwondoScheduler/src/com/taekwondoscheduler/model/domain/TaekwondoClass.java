package com.taekwondoscheduler.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import com.taekwondoscheduler.model.enums.*;

/**
 * This class represents a Taekwondo class at Mueller's Martial Arts
 */
public class TaekwondoClass implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 1551240116400269453L;

	/**
	 * The day of the week on which the class occurs
	 */
	private Day day;

	/**
	 * The starting time of the class
	 */
	private String time;

	/**
	 * The type of the class
	 */
	private ClassType type;

	/**
	 * The minute length of the class
	 */
	private byte length;

	/**
	 * The attendance class list for the class
	 */
	private ArrayList<String> classList; // List of students attending

	/**
	 * The boolean value for whether the class is visible or not
	 */
	private boolean visible; // visibility for UI

	/**
	 * The corresponding index of the class in the array
	 */
	private byte index;

	/**
	 * Default Constructor for TaekwondoClass
	 */
	public TaekwondoClass() {
		this.day = null;
		this.time = null;
		this.type = null;
		this.length = -1;
		this.classList = null;
		this.visible = false;
		this.index = -1;
	}

	/**
	 * Constructor with fields for TaekwondoClass
	 * 
	 * @param day  the day of the week that class occurs
	 * @param type the type of the class
	 */
	public TaekwondoClass(Day day, ClassType type) {
		this.day = day;
		this.type = type;
		this.setTime(); // Sets the time of the class based on day and type
		this.setLength(); // Sets the length of the class based on the type
		this.classList = new ArrayList<>(); // Empty class list to start
		this.visible = false;
		this.index = -1;
	}

	/**
	 * Gets the day the class occurs on
	 * 
	 * @return the day of the class
	 */
	public Day getDay() {
		return day;
	}

	/**
	 * Sets the day the TaekwondoClass occurs on
	 * 
	 * @param day the new day to be set
	 */
	public void setDay(Day day) {
		this.day = day;
	}

	/**
	 * Gets the time the TaekwondoClass begins
	 * 
	 * @return the time the TaekwondoClass begins
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the time the TaekwondoClass begins, based on the day and type Cannot
	 * succeed if these fields are null Based on the schedule at
	 * https://muellersmartialarts.com/class-schedule/
	 * 
	 * @return true if successfully set, false if not
	 */
	public boolean setTime() {

		boolean set = true; // assume successful

		// if fields are null, time cannot be set
		if (this.day == null || this.type == null) {
			set = false;
		} else {

			if (this.day == Day.MONDAY) { // Monday Schedule

				if (this.type == ClassType.KARATE_KIDS) {
					this.time = "4:00"; // Karate Kids Class is at 4:00 p.m.
				} else if (this.type == ClassType.WEAPONS) {
					this.time = "4:50"; // Weapons Class is at 4:50 p.m.
				} else if (this.type == ClassType.BLACK_BELT) {
					this.time = "5:25"; // Black Belt Class is at 5:25 p.m.
				} else {
					set = false; // Invalid class type
				}

			} else if (this.day == Day.TUESDAY || this.day == Day.THURSDAY) { // Tuesday and Thursday Schedule

				if (this.type == ClassType.FAMILY) {
					this.time = "4:00"; // Family Class is at 4:00 p.m.
				} else if (this.type == ClassType.LITTLE_DRAGONS) {
					this.time = "4:50"; // Little Dragons Class is at 4:50 p.m.
				} else if (this.type == ClassType.KARATE_KIDS) {
					this.time = "5:25"; // Karate Kids Class is at 5:25 p.m.
				} else if (this.type == ClassType.TEEN_ADULT) {
					this.time = "6:15"; // Teen/Adult Class is at 6:15 p.m.
				} else {
					set = false; // Invalid class type
				}

			} else if (this.day == Day.WEDNESDAY) { // Wednesday Schedule

				if (this.type == ClassType.LITTLE_DRAGONS) {
					this.time = "4:30"; // Little Dragons Class is at 4:30 p.m.
				} else if (this.type == ClassType.KARATE_KIDS) {
					this.time = "5:05"; // Karate Kids Class is at 5:05 p.m.
				} else if (this.type == ClassType.NXT) {
					this.time = "5:55"; // NXT Class is at 5:55 p.m.
				} else if (this.type == ClassType.BLACK_BELT) {
					this.time = "6:30"; // Black Belt Class is at 6:30 p.m.
				} else {
					set = false; // Invalid class type
				}

			} else if (this.day == Day.FRIDAY) { // Friday Schedule
				if (this.type == ClassType.FAMILY) {
					this.time = "4:00"; // Family Class is at 4:00 p.m.
				} else if (this.type == ClassType.SPARRING_A) {
					this.time = "4:50"; // Sparring A Class is at 4:50 p.m.
				} else if (this.type == ClassType.SPARRING_B) {
					this.time = "5:25"; // Sparring B Class is at 5:25 p.m.
				} else if (this.type == ClassType.COMP_TEAM) {
					this.time = "6:00"; // Comp Team is at 6:00 p.m.
				} else {
					set = false; // Invalid class type
				}
			}
		}

		return set;
	}

	/**
	 * Gets the type of the TaekwondoClass
	 * 
	 * @return the type of the TaekwondoClass
	 */
	public ClassType getType() {
		return type;
	}

	/**
	 * Sets the type of the TaekwondoClass
	 * 
	 * @param type the new type to be set
	 */
	public void setType(ClassType type) {
		this.type = type;
	}

	/**
	 * Gets the length of the TaekwondoClass
	 * 
	 * @return the length of the TaekwondoClass
	 */
	public byte getLength() {
		return length;
	}

	/**
	 * Sets the length of the TaekwondoClass based on its type Cannot succeed if
	 * type is null Based on the schedule at
	 * https://muellersmartialarts.com/class-schedule/
	 * 
	 * @return true if successfully set, false if not
	 */
	public boolean setLength() {

		boolean set = true; // assume successful

		// if type is null, length cannot be set
		if (this.type == null) {
			set = false;
		} else {

			if (this.type == ClassType.LITTLE_DRAGONS || this.type == ClassType.WEAPONS || this.type == ClassType.NXT
					|| this.type == ClassType.SPARRING_A || this.type == ClassType.SPARRING_B) {

				this.length = 30; // These classes are 30 minutes long

			} else if (this.type == ClassType.FAMILY || this.type == ClassType.KARATE_KIDS
					|| this.type == ClassType.TEEN_ADULT || this.type == ClassType.BLACK_BELT) {

				this.length = 45; // These classes are 45 minutes long

			} else if (this.type == ClassType.COMP_TEAM) {
				this.length = 60; // Comp Team is 60 minutes long

			}
		}
		return set;
	}

	/**
	 * Gets the class list of the TaekwondoClass
	 * 
	 * @return the class list
	 */
	public ArrayList<String> getClasslist() {
		return classList;
	}

	/**
	 * Sets the class list of the TaekwondoClass
	 * 
	 * @param classlist the class list to set
	 */
	public void setClasslist(ArrayList<String> classList) {
		this.classList = classList;
	}

	/**
	 * Checks if the class is visible to the user of the UI
	 * 
	 * @return true if visible, false if not
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Sets whether the class is visible to the user of the UI
	 * 
	 * @param visible the boolean value for whether the class is visible or not
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	

	/**
	 * @return the index
	 */
	public byte getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(byte index) {
		this.index = index;
	}

	@Override
	public int hashCode() {
		return Objects.hash(classList, day, index, length, time, type, visible);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaekwondoClass other = (TaekwondoClass) obj;
		return Objects.equals(classList, other.classList) && day == other.day && index == other.index
				&& length == other.length && Objects.equals(time, other.time) && type == other.type
				&& visible == other.visible;
	}

	@Override
	public String toString() {
		return "TaekwondoClass [day=" + day + ", time=" + time + ", type=" + type + ", length=" + length
				+ ", classList=" + classList + ", visible=" + visible + ", index=" + index + "]";
	}

	/**
	 * Checks if every field has valid data values nulls, empty strings, and
	 * negative numbers are invalid Overridden from Instructor Superclass
	 * 
	 * @return true if data is valid, false if not
	 */
	public boolean validate() {

		boolean valid = true; // assume valid

		// Fields evaluated separately for readability
		if (this.day == null) {
			valid = false;
		} else if (this.time == null || this.time.equals("")) {
			valid = false;
		} else if (this.type == null) {
			valid = false;
		} else if (this.length == -1) {
			valid = false;
		} else if (this.classList == null) {
			valid = false;
		} else if (this.index == -1) {
			valid = false;
		}
		return valid;

	}

}
