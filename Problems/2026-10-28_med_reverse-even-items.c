SinglyLinkedListNode* extractAndAppendSponsoredNodes(SinglyLinkedListNode* head) {
    if (head == NULL || head->next == NULL){
        return head;
    }
    SinglyLinkedListNode *even_chain = head;
    SinglyLinkedListNode *new_head = head->next;
    SinglyLinkedListNode *curr_odd = new_head;
    even_chain->next = NULL;
    
    while (true){
        SinglyLinkedListNode* next_even = curr_odd->next;
        if (next_even == NULL) break;
        SinglyLinkedListNode* next_odd = next_even->next;
        curr_odd->next = next_odd;
        next_even->next = even_chain;
        even_chain = next_even; 
        if (next_odd == NULL) break;
        curr_odd = next_odd;
    }
    curr_odd->next = even_chain;
    return new_head;    
}
