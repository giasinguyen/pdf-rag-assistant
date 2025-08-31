## 📖 Project Context

This project delivers a **production-ready Retrieval-Augmented Generation (RAG) system** for question answering over PDF documents, implemented with a modern **Java Spring Boot + AI + Vector Database stack**. It bridges **information retrieval** and **large language models (LLMs)** to provide accurate, explainable, and citation-backed responses.

### 🔎 Ingestion Pipeline

* **PDF Parsing:** Apache PDFBox extracts raw text while preserving structure.
* **Chunking Strategy:** Text is segmented into overlapping windows (800–1000 characters, 150 overlap) to maintain semantic continuity across boundaries.
* **Embedding Generation:** Each chunk is vectorized using **OpenAI’s `text-embedding-3-small`** model (1536-dim float array).
* **Vector Persistence:** Embeddings are stored in **PostgreSQL with pgvector**, enabling similarity search via **IVFFlat / HNSW indexes** optimized for cosine distance.

### ❓ Query Pipeline

* **Question Embedding:** The user query is transformed into an embedding vector with the same dimensionality.
* **Vector Retrieval:** A **k-NN search** (`ORDER BY embedding <=> query LIMIT K`) retrieves the most semantically relevant chunks.
* **Prompt Assembly:** Retrieved chunks are concatenated into a structured context block, injected into a **prompt template**.
* **Answer Generation:** A **Spring AI ChatClient** (OpenAI GPT-4o-mini by default, Ollama optional) generates a grounded response.
* **Citation Attribution:** The system returns both the answer and the corresponding chunk indices, ensuring explainability and traceability.

### 🛠 Tech Stack & Rationale

* **Backend:** Spring Boot 3 (Web, Data JPA, AI integration) – robust, enterprise-grade framework with strong ecosystem support.
* **AI Layer:** Spring AI abstraction, enabling pluggable LLM providers (OpenAI, Ollama).
* **Vector Store:** PostgreSQL + pgvector – lightweight, easy to deploy with Docker, avoids dependency on external vector DB services.
* **PDF Processing:** Apache PDFBox – stable, proven library for text extraction.
* **Frontend:** React + Vite – minimal chat-style interface for document upload and querying.
* **Deployment:** Docker Compose orchestrates PostgreSQL (with pgvector), backend, and frontend containers.

### 🚀 Why This Design Matters

This architecture combines the **deterministic precision of vector search** with the **generative power of LLMs**, addressing the “hallucination problem” by grounding responses in retrieved context. It is easily extensible (multi-doc search, reranking, auth, streaming) and suitable for **enterprise use cases** such as compliance auditing, research assistance, legal Q\&A, and technical documentation support.


