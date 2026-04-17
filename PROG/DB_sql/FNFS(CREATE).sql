DROP DATABASE IF EXISTS FNFS;
CREATE DATABASE FNFS DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_spanish_ci;
USE FNFS;

-- 1. TALDEAK (Ez dago beste ezeren menpe)
CREATE TABLE taldeak (
    Talde_Izena VARCHAR(20) NOT NULL PRIMARY KEY,
    Sorrera_Data DATE NOT NULL,
    Lehendakari_Izena VARCHAR(10) NOT NULL,
    Lehendakari_NAN CHAR(9) NOT NULL UNIQUE,
    N_Bazkideak INT NOT NULL CHECK(N_Bazkideak BETWEEN 0 AND 99999)
);

-- 2. JOKALARIAK (Taldeen menpe)
CREATE TABLE jokalariak (
    NAN CHAR(9) NOT NULL PRIMARY KEY,
    Jok_Izena VARCHAR(30) NOT NULL,
    Jok_Abizena VARCHAR(30) NOT NULL,
    Jaio_Data DATE NOT NULL,
    Merka_Prezioa DECIMAL(10, 2) NOT NULL CHECK (Merka_Prezioa >= 0),
    Talde_Izena VARCHAR(20) NOT NULL,
    Posizioa VARCHAR(20) DEFAULT 'Desconocido',
    FOREIGN KEY(Talde_Izena) REFERENCES taldeak(Talde_Izena) ON UPDATE CASCADE
);

-- 3. DENBORALDIA (Kutxa handia - Ez dago beste ezeren menpe)
CREATE TABLE denboraldia (
    Id_Denbo INT NOT NULL PRIMARY KEY,
    Denbo_urtea INT(4) NOT NULL
);

-- 4. KLASIFIKAZIOA (Taldeen eta Denboraldiaren menpe)
CREATE TABLE klasifikazioa (
    Id_Klasi INT NOT NULL CHECK (Id_Klasi > 0),
    Id_Denbo INT NOT NULL CHECK (Id_Denbo > 0),
    Talde_Izena VARCHAR(20) NOT NULL,
    Puntutotalak INT DEFAULT 0 CHECK (Puntutotalak >= 0),
    Par_Irabaziak INT DEFAULT 0 CHECK (Par_Irabaziak >= 0),
    Par_Galdutak INT DEFAULT 0 CHECK (Par_Galdutak >= 0),
    Puntos_F INT DEFAULT 0 CHECK (Puntos_F >= 0),
    Puntos_C INT DEFAULT 0 CHECK (Puntos_C >= 0),
    PRIMARY KEY (Id_Klasi),
    FOREIGN KEY (Talde_Izena) REFERENCES taldeak(Talde_Izena),
    FOREIGN KEY (Id_Denbo) REFERENCES denboraldia(Id_Denbo)
);

-- 5. JAURDUNALDIA (Kutxa ertaina - Denboraldiaren menpe)
CREATE TABLE jaurdunaldia (
    Id_Jaurdu INT NOT NULL PRIMARY KEY CHECK (Id_Jaurdu > 0),
    Zenbakia INT NOT NULL, -- 1. Jardunaldia, 2. Jardunaldia...
    Id_Denbo INT NOT NULL,
    FOREIGN KEY (Id_Denbo) REFERENCES denboraldia(Id_Denbo)
);

-- 6. PARTIDUAK (Edukia - Jardunaldien eta Taldeen menpe)
CREATE TABLE partiduak (
    Id_Par INT NOT NULL PRIMARY KEY CHECK (Id_Par > 0),
    Id_Jaurdu INT NOT NULL, -- Zein jardunalditakoa den jakiteko
    Talde_Lok VARCHAR(20) NOT NULL,
    Talde_Bis VARCHAR(20) NOT NULL,
    Result_Lok INT DEFAULT NULL,
    Result_Bis INT DEFAULT NULL,
    ordutegia DATETIME NOT NULL,
    FOREIGN KEY (Id_Jaurdu) REFERENCES jaurdunaldia(Id_Jaurdu),
    FOREIGN KEY (Talde_Lok) REFERENCES taldeak(Talde_Izena),
    FOREIGN KEY (Talde_Bis) REFERENCES taldeak(Talde_Izena),
    CHECK (Talde_Lok <> Talde_Bis)
);