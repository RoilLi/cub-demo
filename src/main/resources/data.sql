CREATE TABLE CUR_CURRENCY_NAME_SETTING(
    ID INT NOT NULL AUTO_INCREMENT,
    CURRENCY_CODE VARCHAR(10) NOT NULL,
    `LANGUAGE` VARCHAR(5) NOT NULL,
    NAME VARCHAR(50),
    CREATE_DATE DATETIME,
    UPDATE_DATE DATETIME
);

CREATE UNIQUE INDEX PK_CUR_CURRENCY_NAME_SETTING_1 ON CUR_CURRENCY_NAME_SETTING
(ID);

CREATE UNIQUE INDEX PK_CUR_CURRENCY_NAME_SETTING_2 ON CUR_CURRENCY_NAME_SETTING
(CURRENCY_CODE, `LANGUAGE`);

INSERT INTO CUR_CURRENCY_NAME_SETTING (CURRENCY_CODE, `LANGUAGE`, NAME, CREATE_DATE)  VALUES
('USD', 'TW', '美元', CURRENT_TIME()),
('GBP', 'TW', '英鎊', CURRENT_TIME()),
('EUR', 'TW', '歐元', CURRENT_TIME());