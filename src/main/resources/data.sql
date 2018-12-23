

-- -- --Bills
-- insert into parking_bill(id, PARKING_FEE, PARKING_TIME_IN_HOURS) values (1, 2.2, 5);
insert into parking_bill values (1, '2018-12-15', 'REGULAR', 'WEQ2123', 2.22, 'FINISHED', 1 );
insert into parking_bill values (2, '2018-12-15', 'REGULAR', 'XFA2124', 3.33, 'FINISHED', 2 );
insert into parking_bill values (3, '2018-12-15', 'REGULAR', 'ASD2123', 4.44, 'FINISHED', 3 );
insert into parking_bill values (4, '2018-12-15', 'REGULAR', 'MGQ2124', 5.55, 'FINISHED', 6 );
--
insert into parking_bill values (5, '2018-12-20', 'REGULAR', 'ASD2123', 4.44, 'FINISHED', 5 );
insert into parking_bill values (6, '2018-12-20', 'REGULAR', 'MGQ2124', 5.55, 'FINISHED', 6 );
--
insert into parking_bill values (7, null, 'REGULAR', 'ASD2123', 0, 'OCCUPIED', 0);
insert into parking_bill values (8, null, 'REGULAR', 'MGQ2124', 0, 'OCCUPIED', 0);

--finished parkings
--  12-05
 insert into parking_meter values (1, 'KJS-417', '2018-12-15 13:20:00', '2018-12-05 15:46:06', 1);
 insert into parking_meter values (2, 'ZXC-417', '2018-12-15 13:20:00', '2018-12-05 16:46:06', 2);
 insert into parking_meter values (3, 'KJS-417', '2018-12-15 13:20:00', '2018-12-05 17:46:06', 3);
 insert into parking_meter values (4, 'KJS-417', '2018-12-15 13:20:00', '2018-12-05 18:46:06', 4);
-- -- 12-20
 insert into parking_meter values (5, 'KJS-417', '2018-12-20 13:20:00', '2018-12-20 17:46:06', 5);
 insert into parking_meter values (6, 'KJS-417', '2018-12-20 13:20:00', '2018-12-20 18:46:06', 6);
-- -- -- --Occupied parkings
 insert into parking_meter values (7, 'TST-123', '2018-12-23 13:20:00', null, 7);
 insert into parking_meter values (8, 'STS-123', '2018-12-23 13:20:00', null, 8);





-- -- --finished parkings
-- -- 12-05
-- insert into parking_meter values (3, 'REGULAR', 'WAW7544Y', 'KJS-417', 'FINISHED', '2018-12-05 13:20:00', '2018-12-05 14:46:06');
-- insert into parking_meter values (4, 'DISABLED', 'GST2717X', 'XGA-817', 'FINISHED', '2018-12-05 16:56:00', '2018-12-05 21:03:12');
-- insert into parking_meter values (5, 'REGULAR', 'GAR6201Z', 'OQW-379', 'FINISHED', '2018-12-05 11:20:00', '2018-12-05 15:26:41');
-- insert into parking_meter values (6, 'DISABLED', 'LAB1821B', 'LRS-921', 'FINISHED', '2018-12-05 017:14:30', '2018-12-06 11:46:45');
--
-- -- 12-10
-- insert into parking_meter values (7, 'REGULAR', 'AZC7544Y', 'FCS-417', 'FINISHED', '2018-12-10 13:20:00', '2018-12-10 14:23:37');
-- insert into parking_meter values (8, 'DISABLED', 'LOL2717X', 'ZVA-817', 'FINISHED', '2018-12-10 16:56:00', '2018-12-10 21:51:26');
-- insert into parking_meter values (9, 'REGULAR', 'XAD6201Z', 'LGW-379', 'FINISHED', '2018-12-10 11:20:00', '2018-12-10 15:26:21');
-- insert into parking_meter values (10, 'DISABLED', 'CBV1821B', 'RTE-921', 'FINISHED', '2018-12-10 017:14:30', '2018-12-10 11:46:11');
--
-- -- --Occupied parkings
-- insert into parking_meter values (13, 'REGULAR', 'WEQ2123', 'AHC-371', 'OCCUPIED', '2018-12-15 16:56:00', NULL);
-- insert into parking_meter values (15, 'REGULAR', 'WDZ2123', 'BAQ-654', 'OCCUPIED', '2018-12-15 16:56:00', NULL);
-- insert into parking_meter values (17, 'DISABLED', 'MBH2123', 'ZCV-427', 'OCCUPIED', '2018-12-15 16:56:00', NULL);



