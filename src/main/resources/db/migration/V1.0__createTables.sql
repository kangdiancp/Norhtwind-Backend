CREATE TABLE Categories
(
    CategoryID INTEGER PRIMARY KEY IDENTITY(1,1),
    CategoryName VARCHAR(25),
    Description VARCHAR(255)
)
GO

CREATE TABLE Customers
(
    CustomerID INTEGER PRIMARY KEY IDENTITY(1,1),
    CustomerName VARCHAR(50),
    ContactName VARCHAR(50),
    Address VARCHAR(50),
    City VARCHAR(20),
    PostalCode VARCHAR(10),
    Country VARCHAR(15)
);
GO

CREATE TABLE Employees
(
    EmployeeID INTEGER PRIMARY KEY IDENTITY(1,1),
    LastName VARCHAR(15),
    FirstName VARCHAR(15),
    BirthDate DATETIME,
    Photo VARCHAR(25),
    Notes VARCHAR(1024)
);
GO

CREATE TABLE Shippers(
    ShipperID INTEGER PRIMARY KEY IDENTITY(1,1),
    ShipperName VARCHAR(25),
    Phone VARCHAR(15)
);
GO

CREATE TABLE Suppliers(
    SupplierID INTEGER PRIMARY KEY IDENTITY(1,1),
    SupplierName VARCHAR(50),
    ContactName VARCHAR(50),
    Address VARCHAR(50),
    City VARCHAR(20),
    PostalCode VARCHAR(10),
    Country VARCHAR(15),
    Phone VARCHAR(15)
);
GO

CREATE TABLE Products(
    ProductID INTEGER PRIMARY KEY IDENTITY(1,1),
    ProductName VARCHAR(50),
    SupplierID INTEGER,
    CategoryID INTEGER,
    Unit VARCHAR(25),
    Price NUMERIC,
	FOREIGN KEY (CategoryID) REFERENCES Categories (CategoryID),
	FOREIGN KEY (SupplierID) REFERENCES Suppliers (SupplierID)
);
GO

CREATE TABLE Orders(
    OrderID INTEGER PRIMARY KEY IDENTITY(10248,1),
    CustomerID INTEGER,
    EmployeeID INTEGER,
    OrderDate DATETIME,
    ShipperID INTEGER,
    FOREIGN KEY (EmployeeID) REFERENCES Employees (EmployeeID),
    FOREIGN KEY (CustomerID) REFERENCES Customers (CustomerID),
    FOREIGN KEY (ShipperID) REFERENCES Shippers (ShipperID)
);
GO

CREATE TABLE OrderDetails(
    order_id INTEGER,
    product_id INTEGER,
    Quantity INTEGER,
    CONSTRAINT pk_order_product_id PRIMARY KEY(order_id,product_id),
	FOREIGN KEY (order_id) REFERENCES Orders (OrderID),
	FOREIGN KEY (product_id) REFERENCES Products (ProductID)
);
GO