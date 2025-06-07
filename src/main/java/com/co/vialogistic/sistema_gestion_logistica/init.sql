-- init.sql
-- Script para configurar permisos correctos

-- Asegurar que el usuario admin tenga todos los permisos
ALTER USER admin CREATEDB;
ALTER USER admin SUPERUSER;

-- Otorgar permisos explícitos en el esquema public
GRANT ALL PRIVILEGES ON SCHEMA public TO admin;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO admin;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO admin;

-- Configurar permisos por defecto para objetos futuros
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO admin;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO admin;

-- Verificar que la base de datos AppVial existe y el usuario tiene acceso
\c AppVial;
GRANT ALL PRIVILEGES ON DATABASE AppVial TO admin;