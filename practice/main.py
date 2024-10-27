from fastapi import FastAPI
from routers import handwriting

app = FastAPI()


app.include_router(handwriting.router)
