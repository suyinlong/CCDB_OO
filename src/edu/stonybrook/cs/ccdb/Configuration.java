/****************************************************************************
	CSE532 -- Project 2
	File name: Congifuration.java
	Authors: 	Yinlong Su (110461173)
				Wei-Ying Tsai (110351414)
	Brief description: Configuration class for project
****************************************************************************/

package edu.stonybrook.cs.ccdb;

public class Configuration {
	public static String URL = "jdbc:postgresql://127.0.0.1:5432/ccdb";
	
	public static String USR = "ccdb";
	public static String PWD = "CSE532A2";
	
	public static String QUERY[] = {
			"SELECT au.id, au.name, s.id, s.name FROM \"Account\" oa, \"getAuthUsers\"(oa) au, \"getOrgOwner\"(oa) oo, \"getSigners\"(oo) s WHERE oa.balance - oa.limit <= 1000 AND oa.balance - oa.limit >= -1000;",
			"SELECT p.id, p.name FROM \"Person\" p WHERE \"getAcctsNum\"(p)>=4 AND \"getAuthAcctsNum\"(p)>=3;",
			"SELECT DISTINCT a.number FROM \"Account\" a, \"getOrgOwner\"(a) oo, \"getSigners\"(oo) s WHERE NOT \"getMaxLimit\"(s) < 25000;",
			"WITH RECURSIVE indirect_user(id, name, number) AS ( SELECT aa.id, aa.name, a.number FROM \"Account\" a, \"getNonOwnerAuthUsers\"(a) aa UNION SELECT aa.id, aa.name, iu.number FROM indirect_user iu, \"Account\" a, \"getNonOwnerAuthUsers\"(a) aa WHERE iu.id = a.owner) SELECT * FROM indirect_user;",
			"WITH RECURSIVE indirect_user(id, name, number) AS ( SELECT aa.id, aa.name, a.number FROM \"Account\" a, \"getNonOwnerAuthUsers\"(a) aa UNION SELECT aa.id, aa.name, iu.number FROM indirect_user iu, \"Account\" a, \"getNonOwnerAuthUsers\"(a) aa WHERE iu.id = a.owner) SELECT sum(a.balance) FROM indirect_user iu, \"Account\" a WHERE iu.name = 'Joe' AND iu.number = a.number;"
	};

}
