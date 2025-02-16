USE [master]
GO
/****** Object:  Database [SocialMediaDB]    Script Date: 2025/2/10 上午 09:22:23 ******/
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'SocialMediaDB')
BEGIN
CREATE DATABASE [SocialMediaDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SocialMediaDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\SocialMediaDB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'SocialMediaDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\SocialMediaDB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
END
GO
ALTER DATABASE [SocialMediaDB] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SocialMediaDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SocialMediaDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SocialMediaDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SocialMediaDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SocialMediaDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SocialMediaDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [SocialMediaDB] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [SocialMediaDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SocialMediaDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SocialMediaDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SocialMediaDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SocialMediaDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SocialMediaDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SocialMediaDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SocialMediaDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SocialMediaDB] SET  ENABLE_BROKER 
GO
ALTER DATABASE [SocialMediaDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SocialMediaDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SocialMediaDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SocialMediaDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SocialMediaDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SocialMediaDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SocialMediaDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SocialMediaDB] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SocialMediaDB] SET  MULTI_USER 
GO
ALTER DATABASE [SocialMediaDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SocialMediaDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SocialMediaDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SocialMediaDB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SocialMediaDB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SocialMediaDB] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [SocialMediaDB] SET QUERY_STORE = ON
GO
ALTER DATABASE [SocialMediaDB] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
/****** Object:  Login [NT SERVICE\Winmgmt]    Script Date: 2025/2/10 上午 09:22:23 ******/
IF NOT EXISTS (SELECT * FROM sys.server_principals WHERE name = N'NT SERVICE\Winmgmt')
CREATE LOGIN [NT SERVICE\Winmgmt] FROM WINDOWS WITH DEFAULT_DATABASE=[master], DEFAULT_LANGUAGE=[繁體中文]
GO
/****** Object:  Login [NT SERVICE\SQLWriter]    Script Date: 2025/2/10 上午 09:22:23 ******/
IF NOT EXISTS (SELECT * FROM sys.server_principals WHERE name = N'NT SERVICE\SQLWriter')
CREATE LOGIN [NT SERVICE\SQLWriter] FROM WINDOWS WITH DEFAULT_DATABASE=[master], DEFAULT_LANGUAGE=[繁體中文]
GO
/****** Object:  Login [NT SERVICE\SQLTELEMETRY$SQLEXPRESS]    Script Date: 2025/2/10 上午 09:22:23 ******/
IF NOT EXISTS (SELECT * FROM sys.server_principals WHERE name = N'NT SERVICE\SQLTELEMETRY$SQLEXPRESS')
CREATE LOGIN [NT SERVICE\SQLTELEMETRY$SQLEXPRESS] FROM WINDOWS WITH DEFAULT_DATABASE=[master], DEFAULT_LANGUAGE=[繁體中文]
GO
/****** Object:  Login [NT Service\MSSQL$SQLEXPRESS]    Script Date: 2025/2/10 上午 09:22:23 ******/
IF NOT EXISTS (SELECT * FROM sys.server_principals WHERE name = N'NT Service\MSSQL$SQLEXPRESS')
CREATE LOGIN [NT Service\MSSQL$SQLEXPRESS] FROM WINDOWS WITH DEFAULT_DATABASE=[master], DEFAULT_LANGUAGE=[繁體中文]
GO
/****** Object:  Login [NT AUTHORITY\SYSTEM]    Script Date: 2025/2/10 上午 09:22:23 ******/
IF NOT EXISTS (SELECT * FROM sys.server_principals WHERE name = N'NT AUTHORITY\SYSTEM')
CREATE LOGIN [NT AUTHORITY\SYSTEM] FROM WINDOWS WITH DEFAULT_DATABASE=[master], DEFAULT_LANGUAGE=[繁體中文]
GO
/****** Object:  Login [LAPTOP-5ET4EQ62\ivan]    Script Date: 2025/2/10 上午 09:22:23 ******/
IF NOT EXISTS (SELECT * FROM sys.server_principals WHERE name = N'LAPTOP-5ET4EQ62\ivan')
CREATE LOGIN [LAPTOP-5ET4EQ62\ivan] FROM WINDOWS WITH DEFAULT_DATABASE=[master], DEFAULT_LANGUAGE=[繁體中文]
GO
/* For security reasons the login is created disabled and with a random password. */
/****** Object:  Login [Ivan]    Script Date: 2025/2/10 上午 09:22:23 ******/
IF NOT EXISTS (SELECT * FROM sys.server_principals WHERE name = N'Ivan')
CREATE LOGIN [Ivan] WITH PASSWORD=N'ov9X0n7yoo+pk9Ebns8jXYnr8Imr/0HnpYe4PVGSFLg=', DEFAULT_DATABASE=[SocialMediaDB], DEFAULT_LANGUAGE=[繁體中文], CHECK_EXPIRATION=OFF, CHECK_POLICY=OFF
GO
ALTER LOGIN [Ivan] DISABLE
GO
/****** Object:  Login [BUILTIN\Users]    Script Date: 2025/2/10 上午 09:22:23 ******/
IF NOT EXISTS (SELECT * FROM sys.server_principals WHERE name = N'BUILTIN\Users')
CREATE LOGIN [BUILTIN\Users] FROM WINDOWS WITH DEFAULT_DATABASE=[master], DEFAULT_LANGUAGE=[繁體中文]
GO
/* For security reasons the login is created disabled and with a random password. */
/****** Object:  Login [##MS_PolicyTsqlExecutionLogin##]    Script Date: 2025/2/10 上午 09:22:23 ******/
IF NOT EXISTS (SELECT * FROM sys.server_principals WHERE name = N'##MS_PolicyTsqlExecutionLogin##')
CREATE LOGIN [##MS_PolicyTsqlExecutionLogin##] WITH PASSWORD=N'PBiLKnKEKGj6HU9/JLLQmGyK2T0isF90Wri5mNmDgxM=', DEFAULT_DATABASE=[master], DEFAULT_LANGUAGE=[us_english], CHECK_EXPIRATION=OFF, CHECK_POLICY=ON
GO
ALTER LOGIN [##MS_PolicyTsqlExecutionLogin##] DISABLE
GO
/* For security reasons the login is created disabled and with a random password. */
/****** Object:  Login [##MS_PolicyEventProcessingLogin##]    Script Date: 2025/2/10 上午 09:22:23 ******/
IF NOT EXISTS (SELECT * FROM sys.server_principals WHERE name = N'##MS_PolicyEventProcessingLogin##')
CREATE LOGIN [##MS_PolicyEventProcessingLogin##] WITH PASSWORD=N'Xb7xfTrVPXsJbzSkkFj0gj2yKPyZmTtNDuWyYBafwFw=', DEFAULT_DATABASE=[master], DEFAULT_LANGUAGE=[us_english], CHECK_EXPIRATION=OFF, CHECK_POLICY=ON
GO
ALTER LOGIN [##MS_PolicyEventProcessingLogin##] DISABLE
GO
ALTER SERVER ROLE [sysadmin] ADD MEMBER [NT SERVICE\Winmgmt]
GO
ALTER SERVER ROLE [sysadmin] ADD MEMBER [NT SERVICE\SQLWriter]
GO
ALTER SERVER ROLE [sysadmin] ADD MEMBER [NT Service\MSSQL$SQLEXPRESS]
GO
ALTER SERVER ROLE [sysadmin] ADD MEMBER [LAPTOP-5ET4EQ62\ivan]
GO
ALTER SERVER ROLE [sysadmin] ADD MEMBER [Ivan]
GO
USE [SocialMediaDB]
GO
/****** Object:  Table [dbo].[Comment]    Script Date: 2025/2/10 上午 09:22:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Comment]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Comment](
	[CommentID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[PostID] [int] NOT NULL,
	[Content] [nvarchar](max) NOT NULL,
	[CreatedAt] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[CommentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[Likes]    Script Date: 2025/2/10 上午 09:22:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Likes]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Likes](
	[likeID] [int] IDENTITY(1,1) NOT NULL,
	[postID] [int] NOT NULL,
	[userID] [int] NOT NULL,
	[createdAt] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[likeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[Post]    Script Date: 2025/2/10 上午 09:22:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Post]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Post](
	[PostID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[Content] [nvarchar](max) NOT NULL,
	[Image] [nvarchar](255) NULL,
	[CreatedAt] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[PostID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
/****** Object:  Table [dbo].[Users]    Script Date: 2025/2/10 上午 09:22:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Users]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Users](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[UserName] [nvarchar](100) NOT NULL,
	[Email] [nvarchar](255) NOT NULL,
	[Password] [nvarchar](255) NOT NULL,
	[PhoneNumber] [nvarchar](15) NOT NULL,
	[CoverImage] [nvarchar](255) NULL,
	[Biography] [nvarchar](max) NULL,
	[salt] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END
GO
SET IDENTITY_INSERT [dbo].[Comment] ON 

INSERT [dbo].[Comment] ([CommentID], [UserID], [PostID], [Content], [CreatedAt]) VALUES (1, 2, 1, N'Great post, Alice!', CAST(N'2025-01-14T21:35:50.257' AS DateTime))
INSERT [dbo].[Comment] ([CommentID], [UserID], [PostID], [Content], [CreatedAt]) VALUES (2, 3, 2, N'Interesting perspective, Bob!', CAST(N'2025-01-14T21:35:50.257' AS DateTime))
INSERT [dbo].[Comment] ([CommentID], [UserID], [PostID], [Content], [CreatedAt]) VALUES (3, 1, 3, N'Thanks for sharing, Charlie!', CAST(N'2025-01-14T21:35:50.257' AS DateTime))
SET IDENTITY_INSERT [dbo].[Comment] OFF
GO
SET IDENTITY_INSERT [dbo].[Post] ON 

INSERT [dbo].[Post] ([PostID], [UserID], [Content], [Image], [CreatedAt]) VALUES (1, 1, N'Alice first post!', NULL, CAST(N'2025-01-14T21:35:47.553' AS DateTime))
INSERT [dbo].[Post] ([PostID], [UserID], [Content], [Image], [CreatedAt]) VALUES (2, 2, N'Bob thoughts on programming.', NULL, CAST(N'2025-01-14T21:35:47.553' AS DateTime))
INSERT [dbo].[Post] ([PostID], [UserID], [Content], [Image], [CreatedAt]) VALUES (3, 3, N'Charlie travel experiences.', NULL, CAST(N'2025-01-14T21:35:47.553' AS DateTime))
INSERT [dbo].[Post] ([PostID], [UserID], [Content], [Image], [CreatedAt]) VALUES (51, 11, N'asd', NULL, CAST(N'2025-02-06T20:48:19.047' AS DateTime))
INSERT [dbo].[Post] ([PostID], [UserID], [Content], [Image], [CreatedAt]) VALUES (53, 11, N'asdf', NULL, CAST(N'2025-02-10T08:47:04.940' AS DateTime))
SET IDENTITY_INSERT [dbo].[Post] OFF
GO
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([UserID], [UserName], [Email], [Password], [PhoneNumber], [CoverImage], [Biography], [salt]) VALUES (1, N'Alice', N'alice@example.com', N'hashedpassword1', N'0912345678', NULL, N'I am Alice!', NULL)
INSERT [dbo].[Users] ([UserID], [UserName], [Email], [Password], [PhoneNumber], [CoverImage], [Biography], [salt]) VALUES (2, N'Bob', N'bob@example.com', N'hashedpassword2', N'0923456789', NULL, N'This is Bob.', NULL)
INSERT [dbo].[Users] ([UserID], [UserName], [Email], [Password], [PhoneNumber], [CoverImage], [Biography], [salt]) VALUES (3, N'Charlie', N'charlie@example.com', N'hashedpassword3', N'0934567890', NULL, N'Hello from Charlie.', NULL)
INSERT [dbo].[Users] ([UserID], [UserName], [Email], [Password], [PhoneNumber], [CoverImage], [Biography], [salt]) VALUES (4, N'David', N'david@example.com', N'E29X7nlJQu2hm8ZkmDmUAVRf0ceOntXj27GiKUCMb5s=', N'0911122233', NULL, N'Hello, I am David!', NULL)
INSERT [dbo].[Users] ([UserID], [UserName], [Email], [Password], [PhoneNumber], [CoverImage], [Biography], [salt]) VALUES (11, N'newUser', N'new@example.com', N'dW6NcUq2Ja0/GHAkOaCi5Jq9Xam1O+rmrUG3uNHPakM=', N'0987654321', NULL, NULL, N'p600XAhnu79QjyPuMTmmgQ==')
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
/****** Object:  Index [UQ_Likes]    Script Date: 2025/2/10 上午 09:22:23 ******/
IF NOT EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[Likes]') AND name = N'UQ_Likes')
ALTER TABLE [dbo].[Likes] ADD  CONSTRAINT [UQ_Likes] UNIQUE NONCLUSTERED 
(
	[postID] ASC,
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK6dotkott2kjsp8vw4d0m25fb7]    Script Date: 2025/2/10 上午 09:22:23 ******/
IF NOT EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[Users]') AND name = N'UK6dotkott2kjsp8vw4d0m25fb7')
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [UK6dotkott2kjsp8vw4d0m25fb7] UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Users__85FB4E38FFC2B7B8]    Script Date: 2025/2/10 上午 09:22:23 ******/
IF NOT EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[Users]') AND name = N'UQ__Users__85FB4E38FFC2B7B8')
ALTER TABLE [dbo].[Users] ADD UNIQUE NONCLUSTERED 
(
	[PhoneNumber] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Users__A9D10534E35FFFA2]    Script Date: 2025/2/10 上午 09:22:23 ******/
IF NOT EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[Users]') AND name = N'UQ__Users__A9D10534E35FFFA2')
ALTER TABLE [dbo].[Users] ADD UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[DF__Comment__Created__5165187F]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[Comment] ADD  DEFAULT (getdate()) FOR [CreatedAt]
END
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[DF__Likes__createdAt__76969D2E]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[Likes] ADD  DEFAULT (getdate()) FOR [createdAt]
END
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[DF__Post__CreatedAt__4D94879B]') AND type = 'D')
BEGIN
ALTER TABLE [dbo].[Post] ADD  DEFAULT (getdate()) FOR [CreatedAt]
END
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Comment_Post]') AND parent_object_id = OBJECT_ID(N'[dbo].[Comment]'))
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [FK_Comment_Post] FOREIGN KEY([PostID])
REFERENCES [dbo].[Post] ([PostID])
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Comment_Post]') AND parent_object_id = OBJECT_ID(N'[dbo].[Comment]'))
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [FK_Comment_Post]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Comment_Users]') AND parent_object_id = OBJECT_ID(N'[dbo].[Comment]'))
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [FK_Comment_Users] FOREIGN KEY([UserID])
REFERENCES [dbo].[Users] ([UserID])
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Comment_Users]') AND parent_object_id = OBJECT_ID(N'[dbo].[Comment]'))
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [FK_Comment_Users]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Post_Comment]') AND parent_object_id = OBJECT_ID(N'[dbo].[Comment]'))
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [FK_Post_Comment] FOREIGN KEY([PostID])
REFERENCES [dbo].[Post] ([PostID])
ON DELETE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Post_Comment]') AND parent_object_id = OBJECT_ID(N'[dbo].[Comment]'))
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [FK_Post_Comment]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Likes_Post]') AND parent_object_id = OBJECT_ID(N'[dbo].[Likes]'))
ALTER TABLE [dbo].[Likes]  WITH CHECK ADD  CONSTRAINT [FK_Likes_Post] FOREIGN KEY([postID])
REFERENCES [dbo].[Post] ([PostID])
ON DELETE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Likes_Post]') AND parent_object_id = OBJECT_ID(N'[dbo].[Likes]'))
ALTER TABLE [dbo].[Likes] CHECK CONSTRAINT [FK_Likes_Post]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Likes_User]') AND parent_object_id = OBJECT_ID(N'[dbo].[Likes]'))
ALTER TABLE [dbo].[Likes]  WITH CHECK ADD  CONSTRAINT [FK_Likes_User] FOREIGN KEY([userID])
REFERENCES [dbo].[Users] ([UserID])
ON DELETE CASCADE
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Likes_User]') AND parent_object_id = OBJECT_ID(N'[dbo].[Likes]'))
ALTER TABLE [dbo].[Likes] CHECK CONSTRAINT [FK_Likes_User]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Post_Users]') AND parent_object_id = OBJECT_ID(N'[dbo].[Post]'))
ALTER TABLE [dbo].[Post]  WITH CHECK ADD  CONSTRAINT [FK_Post_Users] FOREIGN KEY([UserID])
REFERENCES [dbo].[Users] ([UserID])
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Post_Users]') AND parent_object_id = OBJECT_ID(N'[dbo].[Post]'))
ALTER TABLE [dbo].[Post] CHECK CONSTRAINT [FK_Post_Users]
GO
/****** Object:  StoredProcedure [dbo].[DeleteComment]    Script Date: 2025/2/10 上午 09:22:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[DeleteComment]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROCEDURE [dbo].[DeleteComment] AS' 
END
GO
ALTER PROCEDURE [dbo].[DeleteComment]
    @p_commentID BIGINT,
    @p_userID BIGINT
AS
BEGIN
    SET NOCOUNT ON;

    -- 確保該留言屬於當前使用者
    IF EXISTS (SELECT 1 FROM Comment WHERE commentID = @p_commentID AND userID = @p_userID)
    BEGIN
        DELETE FROM Comment WHERE commentID = @p_commentID;
    END
    ELSE
    BEGIN
        THROW 50000, '無權刪除該留言', 1;
    END
END;
GO
/****** Object:  StoredProcedure [dbo].[DeletePost]    Script Date: 2025/2/10 上午 09:22:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[DeletePost]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROCEDURE [dbo].[DeletePost] AS' 
END
GO
ALTER PROCEDURE [dbo].[DeletePost]
    @p_postID BIGINT,
    @p_userID BIGINT
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        BEGIN TRANSACTION;

        -- 確保該發文屬於當前使用者
        IF EXISTS (SELECT 1 FROM Post WHERE postID = @p_postID AND userID = @p_userID)
        BEGIN
            -- 先刪除該發文的所有留言
            DELETE FROM Comment WHERE postID = @p_postID;
            -- 再刪除發文
            DELETE FROM Post WHERE postID = @p_postID;
        END
        ELSE
        BEGIN
            THROW 50000, '無權刪除該發文', 1;
        END

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        THROW 50000, '刪除發文時發生錯誤', 1;
    END CATCH
END;
GO
/****** Object:  StoredProcedure [dbo].[UpdateComment]    Script Date: 2025/2/10 上午 09:22:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[UpdateComment]') AND type in (N'P', N'PC'))
BEGIN
EXEC dbo.sp_executesql @statement = N'CREATE PROCEDURE [dbo].[UpdateComment] AS' 
END
GO
ALTER PROCEDURE [dbo].[UpdateComment]
    @p_commentID BIGINT,
    @p_userID BIGINT,
    @p_newContent NVARCHAR(MAX)
AS
BEGIN
    SET NOCOUNT ON;
    
    -- 檢查該留言是否屬於當前用戶
    IF EXISTS (SELECT 1 FROM Comment WHERE commentID = @p_commentID AND userID = @p_userID)
    BEGIN
        -- 更新留言內容
        UPDATE Comment
        SET content = @p_newContent
        WHERE commentID = @p_commentID;
    END
    ELSE
    BEGIN
        -- 如果不是該用戶的留言，則拋出錯誤
        THROW 50000, '無權編輯該留言', 1;
    END
END;
GO
USE [master]
GO
ALTER DATABASE [SocialMediaDB] SET  READ_WRITE 
GO
