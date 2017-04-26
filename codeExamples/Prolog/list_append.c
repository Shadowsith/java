// Aus der Klausur WS2012
//

node_t*  list_append(node_t *anchor_a, node_t* anchor_b) {
  if ( anchor_a == NULL ) { 
    return anchor_b;
  } else {
    anchor_a -> next = 
      list_append(anchor_a -> next, anchor_b);
    return anchor_a;
  }
}

