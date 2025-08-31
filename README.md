# 📚 PDF Q&A Assistant (RAG with Spring AI)

A minimal **Retrieval-Augmented Generation (RAG)** system that allows users to upload PDFs and ask questions in natural language.  
The system retrieves relevant text chunks from documents, enriches the prompt with context, and generates **answers with citations** using LLMs.

---

## ✨ Features
- Upload PDF → parse → chunk → embed → store in vector database.
- Semantic search with **pgvector** (PostgreSQL).
- Q&A with context-aware answers and **source citations**.
- REST APIs for document upload and querying.
- Minimal React + Vite frontend (chat & upload interface).

---

## 🛠️ Tech Stack
- **Backend:** Spring Boot 3.5.5, Spring AI, Spring Data JPA, Spring Web
- **LLM Provider:** OpenAI (default) / Ollama (local, optional)
- **Vector Store:** PostgreSQL + pgvector
- **PDF Parsing:** Apache PDFBox
- **Frontend:** React + Vite
- **Deployment:** Docker Compose (Postgres + pgvector, backend, frontend)

---

## 📂 Project Structure
