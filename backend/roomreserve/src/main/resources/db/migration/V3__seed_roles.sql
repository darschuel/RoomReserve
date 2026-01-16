INSERT INTO role (name) VALUES
                            ('user'),
                            ('admin')
ON CONFLICT (name) DO NOTHING;