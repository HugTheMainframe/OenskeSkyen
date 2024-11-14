drop table if exists WishList_Wishes;
drop table if exists User_WishList;
drop table if exists WishList;
drop table if exists Wish;
drop table if exists usercustomer;

create table usercustomer(
                             UserID int auto_increment primary key,
                             FullName varchar(100) not null,
                             Mail varchar(100) not null,
                             UserPassWord varchar(100) not null
);

create table WishList(
                         WishListID int auto_increment primary key,
                         WishListName varchar(100) not null,
                         WishListDescription varchar(100) not null,
                         UserID INT,
                         FOREIGN KEY (UserID) REFERENCES usercustomer(UserID) ON DELETE CASCADE
);

create table Wish(
                     WishID int auto_increment primary key,
                     WishName varchar(100) not null,
                     WishPrice Double not null,
                     WishLink varchar(100) not null
);

create table User_WishList(
                              UserID int,
                              WishListID int,
                              FOREIGN KEY (UserID) REFERENCES usercustomer(UserID) ON DELETE CASCADE,
                              FOREIGN KEY (WishListID) REFERENCES WishList(WishListID) ON DELETE CASCADE
);

create table Wishlist_Wishes(
                                WishListID int,
                                WishID int,
                                FOREIGN KEY (WishListID) REFERENCES WishList(WishListID) ON DELETE CASCADE,
                                FOREIGN KEY (WishID) REFERENCES Wish(WishID) ON DELETE CASCADE
);

insert into usercustomer
(FullName, Mail, UserPassWord) VALUES ('Oskar August', 'Oskar@Kea.com', 'Oskar1234');
insert into usercustomer
(FullName, Mail, UserPassWord) VALUES ('Rasmus Carlsen', 'Rasmus@Kea.com', 'Rasmus1234');
insert into usercustomer
(FullName, Mail, UserPassWord) VALUES ('Lucas Modin', 'Lucas@Kea.com', 'Lucas1234');

insert into WishList (WishListName, WishListDescription, UserID) VALUES ('Lucas List', 'weddinglist', 3) ,('Oskar List', 'cake', 1);

insert into Wish (WishName, WishPrice, WishLink) VALUES ('Sko',99.99,'URLLink1');
insert into Wish (WishName, WishPrice, WishLink) VALUES ('bil',10000.01,'URLLinkBil');
insert into Wish (WishName, WishPrice, WishLink) VALUES ('GameBoy',9.99,'URLLinkGameBoy');

insert into User_WishList (UserID, WishListID) values (1,2), (3,1);

insert into WishList_Wishes (WishListID, WishID) values (2,3), (2,1), (1,2);
