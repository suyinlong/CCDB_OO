/*
* @Author: Yinlong Su
* @Date:   2015-10-29 22:06:01
* @Last Modified by:   Yinlong Su
* @Last Modified time: 2015-11-06 19:44:33
*/

--- Query 1 ---
SELECT au.id, au.name, s.id, s.name
FROM "Account" oa, "getAuthUsers"(oa) au, "getOrgOwner"(oa) oo, "getSigners"(oo) s
WHERE oa.balance - oa.limit <= 1000 AND oa.balance - oa.limit >= -1000;

--- Query 2 ---
SELECT p.id, p.name
FROM "Person" p
WHERE "getAcctsNum"(p)>=4 AND "getAuthAcctsNum"(p)>=3;

--- Query 3 ---
SELECT DISTINCT a.number
FROM "Account" a, "getOrgOwner"(a) oo, "getSigners"(oo) s
WHERE NOT "getMaxLimit"(s) < 25000;

--- Query 4 ---
WITH RECURSIVE indirect_user(id, name, number) AS (
    SELECT aa.id, aa.name, a.number
    FROM "Account" a, "getNonOwnerAuthUsers"(a) aa
  UNION
    SELECT aa.id, aa.name, iu.number
    FROM indirect_user iu, "Person" p, "Account" a, "getNonOwnerAuthUsers"(a) aa
    WHERE iu.id = p.id AND a.owner = p.id
)
SELECT *
FROM indirect_user;

--- Query 5 ---
WITH RECURSIVE indirect_user(id, name, number) AS (
    SELECT aa.id, aa.name, a.number
    FROM "Account" a, "getNonOwnerAuthUsers"(a) aa
  UNION
    SELECT aa.id, aa.name, iu.number
    FROM indirect_user iu, "Person" p, "Account" a, "getNonOwnerAuthUsers"(a) aa
    WHERE iu.id = p.id AND a.owner = p.id
)
SELECT sum(a.balance)
FROM indirect_user iu, "Account" a
WHERE iu.name = 'Joe' AND iu.number = a.number;
