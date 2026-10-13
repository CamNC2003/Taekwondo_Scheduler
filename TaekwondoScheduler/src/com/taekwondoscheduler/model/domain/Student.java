package com.taekwondoscheduler.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import com.taekwondoscheduler.model.enums.*;

/**
 * This class represents a Student at Mueller's Martial Arts. It is the subclass
 * of Instructor.
 */
public class Student extends Instructor implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 8713529409858614306L;

	/**
	 * The age of the Student
	 */
	private byte age;

	/**
	 * The belt rank of the student
	 */
	private Rank rank;

	/**
	 * The payment plan of the Student
	 */
	private Plan plan;

	/**
	 * The Boolean value for whether the Student was invited to NXT class
	 */
	private Boolean nxtInvited; // Is object Boolean so it can be null

	/**
	 * The Boolean value for whether the Student was invited to Sparring B class
	 */
	private Boolean sparringBInvited; // Is object Boolean so it can be null

	/**
	 * The Boolean value for whether the Student was invited to Comp Team class
	 */
	private Boolean compTeamInvited; // Is object Boolean so it can be null

	/**
	 * The list of classes the Student Qualifies for
	 */
	private ArrayList<ClassType> qualifedClasses;

	/**
	 * Default constructor for Student. Uses Instructor Constructor.
	 */
	public Student() {
		super();
		this.age = -1;
		this.rank = null;
		this.plan = null;
		this.nxtInvited = null;
		this.sparringBInvited = null;
		this.compTeamInvited = null;
		this.qualifedClasses = null;
	}

	/**
	 * Constructor with fields for Student. Uses Instructor Constructor.
	 * 
	 * @param account          the Account object of the Student
	 * @param age              the age of the Student
	 * @param rank             the belt rank of the Student
	 * @param plan             the payment plan of the Student
	 * @param nxtInvited       the boolean value for whether the Student was invited
	 *                         to NXT
	 * @param sparringBInvited the boolean value for whether the Student was invited
	 *                         to Sparring B
	 * @param compTeamInvited  the boolean value for whether the Student was invited
	 *                         to Comp Team
	 */
	public Student(Account account, byte age, Rank rank, Plan plan, boolean nxtInvited, boolean sparringBInvited,
			boolean compTeamInvited) {

		super(account);
		this.age = age;
		this.rank = rank;
		this.plan = plan;
		this.nxtInvited = nxtInvited;
		this.sparringBInvited = sparringBInvited;
		this.compTeamInvited = compTeamInvited;

		this.setQualifiedClasses(); // Sets the qualified classes based on age, rank, and invites
	}

	/**
	 * Gets the age of the Student
	 * 
	 * @return the age of the Student
	 */
	public byte getAge() {
		return age;
	}

	/**
	 * Sets the age of the Student
	 * 
	 * @param age the new age to be set
	 */
	public void setAge(byte age) {
		this.age = age;
	}

	/**
	 * Gets the belt rank of the Student
	 * 
	 * @return the belt rank of the Student
	 */
	public Rank getRank() {
		return rank;
	}

	/**
	 * Sets the belt rank of the Student
	 * 
	 * @param rank the new belt rank to be set
	 */
	public void setRank(Rank rank) {
		this.rank = rank;
	}

	/**
	 * Gets the payment plan of the Student
	 * 
	 * @return the payment plan of the Student
	 */
	public Plan getPlan() {
		return plan;
	}

	/**
	 * Sets the payment plan of the Student
	 * 
	 * @param plan the new plan to be set
	 */
	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	/**
	 * Checks if the Student has been invited to NXT
	 * 
	 * @return true if invited, false if not
	 */
	public boolean isNxtInvited() {
		return nxtInvited;
	}

	/**
	 * Sets whether the Student has been invited to NXT
	 * 
	 * @param nxtInvited the boolean value for whether the Student was invited to
	 *                   NXT
	 */
	public void setNxtInvited(boolean nxtInvited) {
		this.nxtInvited = nxtInvited;
	}

	/**
	 * Checks if the Student has been invited to Sparring B
	 * 
	 * @return true if invited, false if not
	 */
	public boolean isSparringBInvited() {
		return sparringBInvited;
	}

	/**
	 * Sets whether the Student has been invited to Sparring B
	 * 
	 * @param sparringBInvited the boolean value for whether the Student was invited
	 *                         to Sparring B
	 */
	public void setSparringBInvited(boolean sparringBInvited) {

		if (this.compTeamInvited != null && this.compTeamInvited == true) {

			this.sparringBInvited = true;

		} else {
			this.sparringBInvited = sparringBInvited;
		}

	}

	/**
	 * Checks if the Student has been invited to Comp Team
	 * 
	 * @return true if invited, false if not
	 */
	public boolean isCompTeamInvited() {
		return compTeamInvited;
	}

	/**
	 * Sets whether the Student has been invited to Comp Team
	 * 
	 * @param compTeamInvited the boolean value for whether the Student was invited
	 *                        to Comp Team
	 */
	public void setCompTeamInvited(boolean compTeamInvited) {
		this.compTeamInvited = compTeamInvited;
	}

	/**
	 * Gets the list of classes for which the Student qualifies
	 * 
	 * @return the list of the Student's qualified classes
	 */
	public ArrayList<ClassType> getQualifiedClasses() {

		return this.qualifedClasses;
	}

	/**
	 * Builds and sets the list of qualified classes based on the Student's rank,
	 * age, and invites Cannot succeed if these fields are null
	 * 
	 * @return true if successfully set, false if not
	 */
	public boolean setQualifiedClasses() {
		boolean set = true; // assume successful

		// if fields are null, qualified classes cannot be set
		if (this.rank == null || this.age == -1 || this.nxtInvited == null || this.sparringBInvited == null
				|| this.compTeamInvited == null) {

			set = false;

		} else {
			ArrayList<ClassType> classes = new ArrayList<>();
			classes.add(ClassType.FAMILY); // Family Class for all ranks, all ages

			if (this.age >= 12) {
				classes.add(ClassType.TEEN_ADULT); // Teen/Adult Class for ages 12 and up
				classes.add(ClassType.WEAPONS); // Weapons Class for ages 8 and up

			} else if (this.age >= 8) {
				classes.add(ClassType.KARATE_KIDS); // Karate Kids Class for ages 8-11
				classes.add(ClassType.WEAPONS); // Weapons Class for ages 8 and up

			} else if (this.age >= 4) {
				classes.add(ClassType.LITTLE_DRAGONS); // Little Dragons Class for ages 4-7
			}

			if (this.rank == Rank.RED_BLACK || this.rank == Rank.BLACK) {
				classes.add(ClassType.BLACK_BELT); // Black Belt Class for Red/Black and Black Belts
			}

			if (this.rank != Rank.WHITE && this.rank != Rank.ORANGE && this.rank != Rank.YELLOW || this.age >= 12) {

				classes.add(ClassType.SPARRING_A); // Sparring A Class for green belts and higher, or ages 12 and up.
			}

			if (this.nxtInvited) {

				classes.add(ClassType.NXT); // NXT Class for invited Students
			}

			if (this.sparringBInvited) {
				classes.add(ClassType.SPARRING_B); // Sparring B Class for Invited Students
			}

			if (this.compTeamInvited) {
				classes.add(ClassType.COMP_TEAM); // Comp Team for invited Students
			}

			this.qualifedClasses = classes;
		}

		return set;
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, age, compTeamInvited, nxtInvited, plan, qualifedClasses, rank, sparringBInvited);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return age == other.age && Objects.equals(compTeamInvited, other.compTeamInvited)
				&& Objects.equals(nxtInvited, other.nxtInvited) && plan == other.plan
				&& Objects.equals(qualifedClasses, other.qualifedClasses) && rank == other.rank
				&& Objects.equals(sparringBInvited, other.sparringBInvited) && Objects.equals(account, other.account);
	}

	@Override
	public String toString() {
		return "Student [Account=" + account + ", age=" + age + ", rank=" + rank + ", plan=" + plan + ", nxtInvited="
				+ nxtInvited + ", sparringBInvited=" + sparringBInvited + ", compTeamInvited=" + compTeamInvited
				+ ", qualifedClasses=" + qualifedClasses + "]";
	}

	/**
	 * Checks if every field has valid data values nulls, empty strings, and
	 * negative numbers are invalid Overridden from Instructor Superclass
	 * 
	 * @return true if data is valid, false if not
	 */
	@Override
	public boolean validate() {

		boolean valid = true; // assume valid

		// Fields evaluated separately for readability
		if (!super.validate()) {
			valid = false;
		} else if (this.age < 4) {
			valid = false;
		} else if (this.rank == null) {
			valid = false;
		} else if (this.nxtInvited == null) {
			valid = false;
		} else if (this.sparringBInvited == null) {
			valid = false;
		} else if (this.compTeamInvited == null) {
			valid = false;
		} else if (this.plan == null) {
			valid = false;
		} else if (this.qualifedClasses == null || this.qualifedClasses.isEmpty()) {
			valid = false;
		}

		return valid;

	}
}
