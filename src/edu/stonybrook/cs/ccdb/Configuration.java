package edu.stonybrook.cs.ccdb;

public class Configuration {
	public static String URL = "jdbc:postgresql://127.0.0.1:5432/ccdb";
	
	public static String USR = "ccdb";
	public static String PWD = "CSE532A2";
	
	public static String QUERY[] = {
			"SELECT au.pid, au.name, s.pid, s.name FROM \"OrgAccount\" oa, na_user(oa) au, o_owner(oa) oo, signer(oo) s WHERE oa.balance-oa.limit<=1000 AND oa.balance-oa.limit>=-1000;",
			"SELECT p.pid, p.name FROM \"Person\" p WHERE account_num(p)>=4 AND na_account_num(p)>=3;",
			"SELECT DISTINCT a.number FROM \"Account\" a, o_owner(a) oo, signer(oo) s WHERE NOT max_limit(s) < 25000;",
			"WITH RECURSIVE indirect_user(pid, name, number) AS ( SELECT aa.pid, aa.name, a.number FROM \"Account\" a, non_owner_a_user(a) aa UNION ALL SELECT aa.pid, aa.name, iu.number FROM indirect_user iu, \"Person\" p, \"Account\" a, non_owner_a_user(a) aa WHERE iu.pid=p.pid AND a.owner=p.pid) SELECT DISTINCT * FROM indirect_user ORDER by pid;",
			"WITH RECURSIVE indirect_user(pid, name, number) AS ( SELECT aa.pid, aa.name, a.number FROM \"Account\" a, non_owner_a_user(a) aa UNION ALL SELECT aa.pid, aa.name, iu.number FROM indirect_user iu, \"Person\" p, \"Account\" a, non_owner_a_user(a) aa WHERE iu.pid=p.pid AND a.owner=p.pid) SELECT sum(DISTINCT a.balance) FROM  indirect_user iu, \"Account\" a WHERE iu.name='Joe'  AND iu.number=a.number;"
	};

}
