using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class personClicked : MonoBehaviour
{

    public GameObject background;
    public GameObject oar;
    public Sprite docks;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnMouseDown()
    {
        background.GetComponent<SpriteRenderer>().sprite = docks;
        this.enabled = false;
        oar.SetActive(true);
        Debug.Log("hey");
    }
}
