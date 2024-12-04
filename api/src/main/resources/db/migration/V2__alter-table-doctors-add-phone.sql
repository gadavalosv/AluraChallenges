ALTER TABLE doctors ADD phone VARCHAR(20);
UPDATE doctors SET phone = '' WHERE phone IS NULL;
ALTER TABLE doctors ALTER COLUMN phone SET NOT NULL;