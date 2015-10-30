/*
* @Author: Yinlong Su
* @Date:   2015-10-29 22:06:01
* @Last Modified by:   Yinlong Su
* @Last Modified time: 2015-10-29 22:07:06
*/

--- Query 1 ---
SELECT au.pid, au.name, s.pid, s.name
FROM "OrgAccount" oa, na_user(oa) au, o_owner(oa) oo, signer(oo) s
WHERE oa.balance-oa.limit<=1000 AND oa.balance-oa.limit>=-1000;

--- Query 2 ---
SELECT p.pid, p.name
FROM "Person" p
WHERE account_num(p)>=4 AND na_account_num(p)>=3;

--- Query 3 ---
SELECT DISTINCT a.number
FROM "Account" a, o_owner(a) oo, signer(oo) s
WHERE NOT max_limit(s) < 25000;

--- Query 4 ---
WITH RECURSIVE indirect_user(pid, name, number) AS (
    SELECT aa.pid, aa.name, a.number
    FROM "Account" a, non_owner_a_user(a) aa
  UNION ALL
    SELECT aa.pid, aa.name, iu.number
    FROM indirect_user iu, "Person" p, "Account" a, non_owner_a_user(a) aa
    WHERE iu.pid=p.pid AND a.owner=p.pid
)
SELECT DISTINCT * FROM indirect_user ORDER by pid

--- Query 5 ---
SELECT sum(DISTINCT a.balance) FROM  indirect_user iu, "Account" a WHERE iu.name='Joe'  AND iu.number=a.number;
