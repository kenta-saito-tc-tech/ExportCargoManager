# ExportCargoManager
個人開発のアプリ

## システム概要
- システム名：**Export cargo managemer**
- 輸出貨物の一括管理アプリ

## 機能
- ユーザー登録、ログイン機能
- 貨物の追加、更新、削除（サイズ、重量、値段）
- 向け地、日付（締め日、到着希望日）、条件の登録
- 向け地、日付、予約状況、検索
- 貨物の3D表示
- 混載業務のチーム別に管理
- 契約航空会社の管理（予約済みパレット、予約済みM3、向け地ごとの使用航空機）

## データベース定義 
usersテーブル				
論理カラム名	物理カラム名	型	制約	備考
id	id	serial	primary key	
ログインid	login_id	varchar(255)	unique / not null	
パスワード	password	varchar(255)	not null	
名前	name	varchar(255)	not null	
担当id	responsible_id	int	not null	
				
responsibleテーブル				
論理カラム名	物理カラム名	型	制約	備考
id	id	serial	primary key	
名前	name	varchar(255)	not null	
				
airportテーブル				
論理カラム名	物理カラム名	型	制約	備考
id	id	serial	primary key	
PREFIX	prefix	int	not null	
レターコード	letter_code	varchar(255)	not null	
名前	name	varchar(255)	not null	
国	country	varchar(255)	not null	
月	mon	varchar(255)		
火	tue	varchar(255)		
水	wed	varchar(255)		
木	thu	varchar(255)		
金	fri	varchar(255)		
土	sat	varchar(255)		
日	sun	varchar(255)		
				
destinationテーブル				
論理カラム名	物理カラム名	型	制約	備考
id	id	serial	primary key	
名前	name	varchar(255)	not null	
担当id	responsible_id	int	not null	
				
cargoテーブル				
論理カラム名	物理カラム名	型	制約	備考
id	id	serial	primary key	
名前	name	varchar(255)	not null	
向け地	destination_id	int	not null	
締め日	close_date	varchar(255)		
到着希望日	arrival_date	varchar(255)		
リチウム	lithium	int	not null	1:リチウムあり 2:リチウムなし
サイズ（縦）	heigh	int		
サイズ（横）	width	int		
サイズ（奥行き）	depth	int		
重量	weight	int		
貨物詳細	description	varchar(255)		
予約状況	reserve_status	int	not null	1:済 2:未
登録日時	created_at	datetime		
変更日時	updated_at	datetime		

