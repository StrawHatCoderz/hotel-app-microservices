curl -X POST http://localhost:3001/api/bookings/ \
  -H "Content-Type: application/json" \
  -d '{"hotel_id": "1", "rooms": 2}'