"""empty message

Revision ID: c07e1d0c0945
Revises: 015095395978
Create Date: 2020-05-13 17:05:21.270328

"""
from alembic import op
import sqlalchemy as sa


# revision identifiers, used by Alembic.
revision = 'c07e1d0c0945'
down_revision = '015095395978'
branch_labels = None
depends_on = None


def upgrade():
    # ### commands auto generated by Alembic - please adjust! ###
    op.create_table('Dokter',
    sa.Column('id', sa.Integer(), nullable=False),
    sa.Column('nama', sa.String(length=25), nullable=False),
    sa.Column('spesialis', sa.String(length=20), nullable=False),
    sa.Column('noTelepon', sa.String(length=13), nullable=False),
    sa.Column('creation_date', sa.TIMESTAMP(), server_default=sa.text('CURRENT_TIMESTAMP'), nullable=False),
    sa.PrimaryKeyConstraint('id')
    )
    # ### end Alembic commands ###


def downgrade():
    # ### commands auto generated by Alembic - please adjust! ###
    op.drop_table('Dokter')
    # ### end Alembic commands ###