ALTER TABLE patients ADD active BOOLEAN;
UPDATE patients SET active = TRUE WHERE active IS NULL;
ALTER TABLE patients ALTER COLUMN active SET NOT NULL;